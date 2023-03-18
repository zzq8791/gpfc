package com.example.learn.gp.service;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.learn.gp.bean.*;
import com.example.learn.gp.config.PathDataConfig;
import com.example.learn.gp.dao.GpCompanyInfoDao;
import com.example.learn.gp.gpparam.GpResponse;
import com.example.learn.gp.vo.GpCompanyHotMmHaVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SyncGpTask {


    @Autowired(required=false)
    GpCompanyInfoDao gpCompanyInfoDao;
    @Autowired(required=false)
    GpCompanyHotMmHaService gpCompanyHotMmHaService;
    @Autowired(required=false)
    GpCompanyNonFinancialService gpCompanyNonFinancialService;
    @Autowired(required=false)
    GpCompanyApiCnIndexService gpCompanyApiCnIndexService;
    @Autowired(required=false)
    GpIndexHotMmHaService gpIndexHotMmHaService;
    @Autowired(required=false)
    GpCompanyHyIndustryService gpCompanyHyIndustryService;
    @Autowired(required=false)
    GpCompanyHyIndustryHotMmHaService gpCompanyHyIndustryHotMmHaService;
    @Autowired(required=false)
    GpCompanyIndustriesService gpCompanyIndustriesService;

    private static final String MILLION_UNIT = "万";
    private static final String BILLION_UNIT = "亿";
    private static final BigDecimal ONE_HUNDRED_THOUSAND = new BigDecimal(100000);
    private static final BigDecimal ONE_HUNDRED_MILLION = new BigDecimal(100000000);
    private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);


    /**
     * 保存股票所属对应的行业
     */
    public void syncCompanyIndustries() {
        LambdaQueryWrapper<GpCompanyInfo> wrapper = Wrappers.lambdaQuery(GpCompanyInfo.class);
        wrapper.eq(GpCompanyInfo :: getMutualMarkets,"[\"ha\"]");
        Integer count = gpCompanyInfoDao.selectCount(wrapper);
        log.info("获取基础股票数据条数 :{}",count);
        int size = PageUtil.totalPage(count, 100);
        // 截取一百条
        Page<GpCompanyInfo> page = new Page<>(1,100);
        page.addOrder(OrderItem.desc("id "));
        Page<GpCompanyInfo> gpCompanyInfoPage = gpCompanyInfoDao.selectPage(page, wrapper);
        List<GpCompanyInfo> records = gpCompanyInfoPage.getRecords();

        LocalDateTime now = LocalDateTime.now();
        for(int i = size; i > 0; i--){
            List<GpCompanyInfo> gpCompanyInfos = records.subList((i-1) * 100, ((i) * 100 > count ? count : (i) * 100));
            List<String> codes = gpCompanyInfos.stream().map(GpCompanyInfo::getStockCode).collect(Collectors.toList());
            codes.forEach(c -> {
                saveCompanyIndustry(c,now,gpCompanyInfos);
            });
        }
    }

    private void saveCompanyIndustry(String code , LocalDateTime now, List<GpCompanyInfo> gpCompanyInfos ){
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        json.put("stockCode",code);
        HttpResponse execute = HttpRequest.post(PathDataConfig.sync_api_cn_hy_api_cn_company_industries_url)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyIndustries> gpCompanyIndustries = JSON.parseArray(data.toString(), GpCompanyIndustries.class);
        gpCompanyIndustries.forEach(g -> {
            List<GpCompanyInfo> collect = gpCompanyInfos.stream().filter(c -> c.getStockCode().equals(code)).collect(Collectors.toList());
            if(collect.size() > 0){
                g.setGp_stockCode_name(collect.get(0).getName());
            }
            g.setGp_stockCode(code);
            g.setCreateTime(now);
        });
        gpCompanyIndustriesService.saveBatch(gpCompanyIndustries);
    }


    /**
     * 行业数据互联互通
     */
    @Scheduled(cron="${sync_api_cn_company_hot_mm_ha_date}")
    public void saveIndustryHotMmha(){
        log.info("同步指数互联互通");
        LambdaQueryWrapper<GpCompanyHyIndustry> wrapper = Wrappers.lambdaQuery(GpCompanyHyIndustry.class);
        wrapper.eq(GpCompanyHyIndustry :: getLevel,"three");
        Integer count = gpCompanyHyIndustryService.count(wrapper);
        log.info("获取基础指数股票数据条数 :{}",count);
        int size = PageUtil.totalPage(count, 100);
        // 截取一百条
        Page<GpCompanyHyIndustry> page = new Page<>(1,100);
        page.addOrder(OrderItem.desc("id "));
        Page<GpCompanyHyIndustry> gpCompanyInfoPage = gpCompanyHyIndustryService.page(page, wrapper);
        List<GpCompanyHyIndustry> records = gpCompanyInfoPage.getRecords();

        LocalDateTime now = LocalDateTime.now();
      //  LocalDate localDate =  now.toLocalDate().minusDays(1);

        for(int i = size; i > 0; i--){
            List<GpCompanyHyIndustry> dustryHotMmHas = records.subList((i-1) * 100, ((i) * 100 > count ? count : (i) * 100));
            List<String> codes = dustryHotMmHas.stream().map(GpCompanyHyIndustry::getStockCode).collect(Collectors.toList());
            saveDustHotMmHa(codes,now,records);
        }
    }

    public void saveDustHotMmHa(List<String> codes, LocalDateTime now,List<GpCompanyHyIndustry> dustryHotMmHas){
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        json.put("stockCodes",codes);
        HttpResponse execute = HttpRequest.post(PathDataConfig.sync_api_cn_hy_api_cn_industry_hot_mm_ha_sw_2021_url)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyHyIndustryHotMmHa> gpCompanyInfosTemp = JSON.parseArray(data.toString(), GpCompanyHyIndustryHotMmHa.class);
      //  List<GpCompanyHyIndustryHotMmHa> datas = new ArrayList<>();
        gpCompanyInfosTemp.forEach(ha -> {
            ha.setCreateTime(now);
            ha.setLast_update_date(ha.getLast_update_date().substring(0,10));
            ha.setMm_sha_mc_r(handlePersent(ha.getMm_sha_mc_r()));
            ha.setMm_sha(formatMoney(ha.getMm_sha()));
            ha.setMm_sha_mc_rc_d1(handlePersent(ha.getMm_sha_mc_rc_d1()));
            ha.setMm_sha_mc_rc_d5(handlePersent(ha.getMm_sha_mc_rc_d5()));
            ha.setMm_sha_mc_rc_d20(handlePersent(ha.getMm_sha_mc_rc_d20()));
            ha.setMm_sha_mc_rc_d60(handlePersent(ha.getMm_sha_mc_rc_d60()));
            ha.setMm_sha_mc_rc_d120(handlePersent(ha.getMm_sha_mc_rc_d120()));
            ha.setMm_sh_nba_d1(formatMoney(ha.getMm_sh_nba_d1()));
            ha.setMm_sh_nba_d5(formatMoney(ha.getMm_sh_nba_d5()));
            ha.setMm_sh_nba_d20(formatMoney(ha.getMm_sh_nba_d20()));
            ha.setMm_sh_nba_d60(formatMoney(ha.getMm_sh_nba_d60()));
            ha.setMm_sh_nba_d120(formatMoney(ha.getMm_sh_nba_d120()));
            ha.setSpc(handlePersent(ha.getSpc()));
            ha.setStockCode(ha.getStockCode());
            GpCompanyHyIndustry gpIndexHotMmHa = dustryHotMmHas.stream().filter(r -> r.getStockCode().equalsIgnoreCase(ha.getStockCode())).collect(Collectors.toList()).get(0);
            String name =gpIndexHotMmHa.getName();
            ha.setName(name);
        });
        gpCompanyHyIndustryHotMmHaService.saveBatch(gpCompanyInfosTemp);
    }

    @Scheduled(cron="${sync_api_cn_company_base_date}")
    public void syncBaseInfo() {
        log.info("同步股票基础信息开始");
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        HttpResponse execute = HttpRequest.post(PathDataConfig.stBaseUrl)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyInfo> gpCompanyInfos = JSON.parseArray(data.toString(), GpCompanyInfo.class);
        LocalDateTime now = LocalDateTime.now();
        log.info("同步股票基础信息开始,获取数据条数 : {}",gpCompanyInfos.size());
        gpCompanyInfos.forEach(d -> {
            LambdaQueryWrapper<GpCompanyInfo> wapper = new LambdaQueryWrapper();
            wapper.eq(GpCompanyInfo :: getStockCode,d.getStockCode());
            List<GpCompanyInfo> infos = gpCompanyInfoDao.selectList(wapper);
            if(infos.size() == 0){
                d.setCreateTime(now);
                gpCompanyInfoDao.insert(d);
            }
        });
        log.info("同步股票基础信息开始,获取数据条数 : {}",gpCompanyInfos.size());

        // 获取指数详细信息。
        syncApiCnIndex();
        // 获取行业数据
        syncApiIndustry();
    }


    /**
     * 获取指数详细信息。
     */
    public void syncApiCnIndex(){
        log.info("获取指数详细信息开始");
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        HttpResponse execute = HttpRequest.post(PathDataConfig.syncApiCnCompanyApiCnIndexUrl)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyApiCnIndex> gpCompanyApiCnIndexs = JSON.parseArray(data.toString(), GpCompanyApiCnIndex.class);
        LocalDateTime now = LocalDateTime.now();
        log.info("获取指数详细信息,获取数据条数 : {}",gpCompanyApiCnIndexs.size());
        List<GpCompanyApiCnIndex> indexList = new ArrayList<>();
        gpCompanyApiCnIndexs.forEach(d -> {
            LambdaQueryWrapper<GpCompanyApiCnIndex> wapper = new LambdaQueryWrapper();
            wapper.eq(GpCompanyApiCnIndex :: getStockCode,d.getStockCode());
            List<GpCompanyApiCnIndex> infos = gpCompanyApiCnIndexService.list(wapper);
            if(infos.size() == 0){
                d.setCreateTime(now);
                d.setLaunchDate(d.getLaunchDate().substring(0,10));
                d.setCreateTime(now);
                indexList.add(d);
             //   gpCompanyApiCnIndexService.insert(d);
            }
        });
        gpCompanyApiCnIndexService.saveBatch(indexList);
        log.info("获取指数详细信息,获取数据条数 : {}",gpCompanyApiCnIndexs.size());
    }

    /**
     * 获取行业数据
     */
    public void syncApiIndustry(){
        log.info("获取获取行业数据信息开始");
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        json.put("source","sw_2021");
        HttpResponse execute = HttpRequest.post(PathDataConfig.sync_api_cn_hy_api_cn_industry_url)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyHyIndustry> gpCompanyApiCnIndexs = JSON.parseArray(data.toString(), GpCompanyHyIndustry.class);
        LocalDateTime now = LocalDateTime.now();
        log.info("获取获取行业数据详细信息,获取数据条数 : {}",gpCompanyApiCnIndexs.size());
        List<GpCompanyHyIndustry> hyList = new ArrayList<>();
        gpCompanyApiCnIndexs.forEach(d -> {
            LambdaQueryWrapper<GpCompanyHyIndustry> wapper = new LambdaQueryWrapper();
            wapper.eq(GpCompanyHyIndustry :: getStockCode,d.getStockCode());
            List<GpCompanyHyIndustry> infos = gpCompanyHyIndustryService.list(wapper);
            if(infos.size() == 0){
                d.setCreateTime(now);
                d.setCreateTime(now);
                hyList.add(d);
            }
        });
        gpCompanyHyIndustryService.saveBatch(hyList);
        log.info("获取获取行业数据详细信息,获取数据条数 : {}",gpCompanyApiCnIndexs.size());
    }

    /**
     * 股票数据互联互通
     */
    @Scheduled(cron="${sync_api_cn_company_hot_mm_ha_date}")
    public void saveMaData() {
        log.info("同步互联互通");
        LambdaQueryWrapper<GpCompanyInfo> wrapper = Wrappers.lambdaQuery(GpCompanyInfo.class);
        wrapper.eq(GpCompanyInfo :: getMutualMarkets,"[\"ha\"]");
        Integer count = gpCompanyInfoDao.selectCount(wrapper);
        log.info("获取基础股票数据条数 :{}",count);
        int size = PageUtil.totalPage(count, 100);
        // 截取一百条
        Page<GpCompanyInfo> page = new Page<>(1,100);
        page.addOrder(OrderItem.desc("id "));
        Page<GpCompanyInfo> gpCompanyInfoPage = gpCompanyInfoDao.selectPage(page, wrapper);
        List<GpCompanyInfo> records = gpCompanyInfoPage.getRecords();

        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate =  now.toLocalDate().minusDays(1);
        for(int i = size; i > 0; i--){
            List<GpCompanyInfo> gpCompanyInfos = records.subList((i-1) * 100, ((i) * 100 > count ? count : (i) * 100));
            List<String> codes = gpCompanyInfos.stream().map(GpCompanyInfo::getStockCode).collect(Collectors.toList());

            // 获取基本面数据，如PE、PB等。
            saveNonFinancial(codes,localDate,now,records);
            // 保存互联互通数据
            saveHotMmHa(codes,  now, records);
        }
    }

    /**
     * 获取指数互联互通数据
     */
    @Scheduled(cron="${sync_api_cn_company_hot_mm_ha_date}")
    public void saveIndexHotMmha(){
        log.info("同步指数互联互通");
        LambdaQueryWrapper<GpCompanyApiCnIndex> wrapper = Wrappers.lambdaQuery(GpCompanyApiCnIndex.class);
        Integer count = gpCompanyApiCnIndexService.count(wrapper);
        log.info("获取基础指数股票数据条数 :{}",count);
        int size = PageUtil.totalPage(count, 100);
        // 截取一百条
        Page<GpCompanyApiCnIndex> page = new Page<>(1,100);
        page.addOrder(OrderItem.desc("id "));
        Page<GpCompanyApiCnIndex> gpCompanyInfoPage = gpCompanyApiCnIndexService.page(page, wrapper);
        List<GpCompanyApiCnIndex> records = gpCompanyInfoPage.getRecords();

        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate =  now.toLocalDate().minusDays(1);

        for(int i = size; i > 0; i--){
            List<GpCompanyApiCnIndex> gpIndexHotMmHas = records.subList((i-1) * 100, ((i) * 100 > count ? count : (i) * 100));
            List<String> codes = gpIndexHotMmHas.stream().map(GpCompanyApiCnIndex::getStockCode).collect(Collectors.toList());
            saveIndexHotMmHa(codes,now,records);
        }
    }

    public void saveIndexHotMmHa(List<String> codes, LocalDateTime now,List<GpCompanyApiCnIndex> records){
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        json.put("stockCodes",codes);
        HttpResponse execute = HttpRequest.post(PathDataConfig.syncApiCnIndexHotMmHaUrl)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpIndexHotMmHa> gpCompanyInfosTemp = JSON.parseArray(data.toString(), GpIndexHotMmHa.class);
        List<GpIndexHotMmHa> datas = new ArrayList<>();
        gpCompanyInfosTemp.forEach(ha -> {
            ha.setCreateTime(now);
            ha.setLast_update_date(ha.getLast_update_date().substring(0,10));
            ha.setMm_sha_mc_r(handlePersent(ha.getMm_sha_mc_r()));
            ha.setMm_sha(formatMoney(ha.getMm_sha()));
            ha.setMm_sha_mc_rc_d1(handlePersent(ha.getMm_sha_mc_rc_d1()));
            ha.setMm_sha_mc_rc_d5(handlePersent(ha.getMm_sha_mc_rc_d5()));
            ha.setMm_sha_mc_rc_d20(handlePersent(ha.getMm_sha_mc_rc_d20()));
            ha.setMm_sha_mc_rc_d60(handlePersent(ha.getMm_sha_mc_rc_d60()));
            ha.setMm_sha_mc_rc_d120(handlePersent(ha.getMm_sha_mc_rc_d120()));
            ha.setMm_sh_nba_d1(formatMoney(ha.getMm_sh_nba_d1()));
            ha.setMm_sh_nba_d5(formatMoney(ha.getMm_sh_nba_d5()));
            ha.setMm_sh_nba_d20(formatMoney(ha.getMm_sh_nba_d20()));
            ha.setMm_sh_nba_d60(formatMoney(ha.getMm_sh_nba_d60()));
            ha.setMm_sh_nba_d120(formatMoney(ha.getMm_sh_nba_d120()));
            ha.setSpc(handlePersent(ha.getSpc()));
            ha.setStockCode(ha.getStockCode());
            GpCompanyApiCnIndex gpIndexHotMmHa = records.stream().filter(r -> r.getStockCode().equalsIgnoreCase(ha.getStockCode())).collect(Collectors.toList()).get(0);
            String name =gpIndexHotMmHa.getName();
            ha.setName(name);
            datas.add(ha);
        });
        gpIndexHotMmHaService.saveBatch(datas);
    }


    public void saveHotMmHa(List<String> codes, LocalDateTime now,List<GpCompanyInfo> records){
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        json.put("stockCodes",codes);
        HttpResponse execute = HttpRequest.post(PathDataConfig.stCompanyMmHaUrl)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyHotMmHaVo> gpCompanyInfosTemp = JSON.parseArray(data.toString(), GpCompanyHotMmHaVo.class);
        List<GpCompanyHotMmHa> datas = new ArrayList<>();
        gpCompanyInfosTemp.forEach(d -> {
            GpCompanyHotMmHa ha = new GpCompanyHotMmHa();
            ha.setCreateTime(now);
            ha.setLastUpdateDate(d.getLast_update_date().substring(0,10));
            ha.setMmSh(formatMoney(d.getMm_sh()));
            ha.setMmShCapR(handlePersent(d.getMm_sh_cap_r()));
            ha.setMmSha(formatMoney(d.getMm_sha()));
            ha.setMmShCapRcD1(handlePersent(d.getMm_sh_cap_rc_d1()));
            ha.setMmShCapRcD5(handlePersent(d.getMm_sh_cap_rc_d5()));
            ha.setMmShCapRcD20(handlePersent(d.getMm_sh_cap_rc_d20()));
            ha.setMmShCapRcD60(handlePersent(d.getMm_sh_cap_rc_d60()));
            ha.setMmShCapRcD120(handlePersent(d.getMm_sh_cap_rc_d120()));
            ha.setMmShcVR(handlePersent(d.getMm_shc_v_r()));
            ha.setMmShNbaD1(formatMoney(d.getMm_sh_nba_d1()));
            ha.setMmShNbaD20(formatMoney(d.getMm_sh_nba_d20()));
            ha.setMmShNbaD60(formatMoney(d.getMm_sh_nba_d60()));
            ha.setMmShNbaD120(formatMoney(d.getMm_sh_nba_d120()));
            ha.setMmShNbaD5(formatMoney(d.getMm_sh_nba_d5()));
            ha.setSpc(handlePersent(d.getSpc()));
            ha.setStockCode(d.getStockCode());
            GpCompanyInfo gpCompanyInfo = records.stream().filter(r -> r.getStockCode().equalsIgnoreCase(d.getStockCode())).collect(Collectors.toList()).get(0);
            String name =gpCompanyInfo.getName();
            ha.setName(name);
           // gpCompanyHotMmHaDao.insert(ha);
            datas.add(ha);
        });
        gpCompanyHotMmHaService.saveBatch(datas);
    }


    public void saveNonFinancial(List<String> codes, LocalDate localDate, LocalDateTime now,List<GpCompanyInfo> records){
        String strs[] = {"ey","dyr","sp","spc","tv","ta","to_r","fc_rights","bc_rights","lxr_fc_rights","shn","mc","cmc","ecmc","ecmc_psh","fb","sb","ha_sh","ha_shm"};
        Map<String, String> headers = new HashMap<>();
        JSONObject json = new JSONObject();
        json.put("token",PathDataConfig.stToken);
        json.put("stockCodes",codes);
        json.put("metricsList", Arrays.asList(strs));
        json.put("date", localDate.toString());
        HttpResponse execute = HttpRequest.post(PathDataConfig.fundamentalNonFinancialUrl)
                .body(json.toJSONString()).addHeaders(headers).execute();
        String ret = execute.body();
        GpResponse res = JSON.parseObject(ret,GpResponse.class);
        Object data = res.getData();
        List<GpCompanyNonFinancial> gpCompanyNonFinancials = JSON.parseArray(data.toString(), GpCompanyNonFinancial.class);
        gpCompanyNonFinancials.forEach(d -> {
            d.setSpc(handlePersent(d.getSpc()));
            d.setEy(handlePersent(d.getEy()));
            d.setDyr(handlePersent(d.getDyr()));
            d.setTv(formatMoney(d.getTv()));
            d.setTa(formatMoney(d.getTa()));
            d.setTo_r(handlePersent(d.getTo_r()));
            d.setShn(formatMoney(d.getShn()));
            d.setMc(formatMoney(d.getMc()));
            d.setCmc(formatMoney(d.getCmc()));
            d.setEcmc(formatMoney(d.getEcmc()));
            d.setEcmc_psh(formatMoney(d.getEcmc_psh()));
            d.setFb(formatMoney(d.getFb()));
            d.setSb(formatMoney(d.getSb()));
            d.setHa_sh(formatMoney(d.getHa_sh()));
            d.setHa_shm(formatMoney(d.getHa_shm()));
            d.setCreateTime(now);
            d.setDate(d.getDate().substring(0,10));
            GpCompanyInfo gpCompanyInfo = records.stream().filter(r -> r.getStockCode().equalsIgnoreCase(d.getStockCode())).collect(Collectors.toList()).get(0);
            String name =gpCompanyInfo.getName();
            d.setName(name);
        });
        gpCompanyNonFinancialService.saveNonFinancial(gpCompanyNonFinancials);
    }


    private static String formatMoney(String str){
        if(StringUtils.isBlank(str)){
            return "";
        }
        return amountConversion(BigDecimal.valueOf(Double.valueOf(str)));
    }

    private static String handlePersent(String str){
        if(StringUtils.isBlank(str)){
            return "";
        }
        return NumberUtil.formatPercent((Double.valueOf(str)), 3);
    }

    /**
     * 将数字转换成以万为单位或者以亿为单位，因为在前端数字太大显示有问题
     * @param amount
     * @return
     */
    public static String amountConversion(BigDecimal amount){
        if (amount == null) {
            return null;
        }
        if (amount.abs().compareTo(ONE_HUNDRED_THOUSAND) < 0) {
            //如果小于10万
            return amount.stripTrailingZeros().toPlainString();
        }
        if (amount.abs().compareTo(ONE_HUNDRED_MILLION) < 0) {
            //如果大于10万小于1亿
            return amount.divide(TEN_THOUSAND, 4, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString() + MILLION_UNIT;
        }
        return amount.divide(ONE_HUNDRED_MILLION, 4, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString() + BILLION_UNIT;
    }

}
