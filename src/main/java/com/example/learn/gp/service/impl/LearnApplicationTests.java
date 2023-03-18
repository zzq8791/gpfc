/*
package com.example.learn.gp.service.impl;

import cn.hutool.core.util.PageUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.learn.gp.bean.GpCompanyHotMmHa;
import com.example.learn.gp.bean.GpCompanyInfo;
import com.example.learn.gp.dao.GpCompanyHotMmHaDao;
import com.example.learn.gp.dao.GpCompanyInfoDao;
import com.example.learn.gp.gpparam.GpResponse;
import com.example.learn.gp.vo.GpCompanyHotMmHaVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class LearnApplicationTests {

	@Autowired(required=false)
    GpCompanyInfoDao gpCompanyInfoDao;
	@Autowired(required=false)
	GpCompanyHotMmHaDao gpCompanyHotMmHaDao;
	@Test
	void contextLoads() {

		String url = "https://open.lixinger.com/api/cn/company";
		Map<String, String> headers = new HashMap<>();
//		headers.put("Accept","application/json");
//		headers.put("Content-Type","application/json");
//		headers.put("api-version","2.0.0");
//		headers.put("appType","1");
		JSONObject json = new JSONObject();
		json.put("token","9e26414e-481d-468b-98fe-f765dea84fc0");
		HttpResponse execute = HttpRequest.post(url)
				.body(json.toJSONString()).addHeaders(headers).execute();
		int status = execute.getStatus();
		String ret = execute.body();
		System.out.println(ret);

		GpResponse res = JSON.parseObject(ret,GpResponse.class);
		//ObjectMapper objectMapper = new ObjectMapper();
		//GpResponse<List<GpCompanyInfo>> listResultDTO = objectMapper.readValue(ret, new TypeReference<GpResponse<List<GpCompanyInfo>>>(){});
		Object data = res.getData();
		List<GpCompanyInfo> gpCompanyInfos = JSON.parseArray(data.toString(), GpCompanyInfo.class);
		LocalDateTime now = LocalDateTime.now();
		gpCompanyInfos.forEach(d -> {
		//	GpCompanyInfo info = JSONObject.parseObject(,GpCompanyInfo.class)
//			String s = JSON.toJSONString(d); //json转换有问题，需要重新转，并指明类型
//			GpCompanyInfo info = JSON.parseObject(s,GpCompanyInfo.class);
			d.setCreateTime(now);
			gpCompanyInfoDao.insert(d);
		});
		System.out.println(res.getData());

	}

	@Test
	void saveGp() {

		String data = "{\"areaCode\":\"cn\",\"fsType\":\"non_financial\",\"ipoDate\":\"2020-11-23T16:00:00.000Z\",\"market\":\"a\",\"name\":\"诺思兰德\",\"stockCode\":\"430047\"}";
		GpCompanyInfo gpCompanyInfo = JSON.parseObject(data, GpCompanyInfo.class);
		System.out.println(gpCompanyInfo);
		gpCompanyInfoDao.insert(gpCompanyInfo);


	}

	@DisplayName("save 互联互通")
    @Test
    void saveMa() {
        LambdaQueryWrapper<GpCompanyInfo> wrapper = Wrappers.lambdaQuery(GpCompanyInfo.class);
        wrapper.eq(GpCompanyInfo :: getMutualMarkets,"[\"ha\"]");
        Integer count = gpCompanyInfoDao.selectCount(wrapper);
        System.out.println(count);
        int size = PageUtil.totalPage(count, 100);
        System.out.println();
       */
/* for(int i = size; i > 0; i--){
            Page<GpCompanyInfo> page = new Page<>(i,100);
            page.addOrder(OrderItem.desc("id "));
            Page<GpCompanyInfo> gpCompanyInfoPage = gpCompanyInfoDao.selectPage(page, wrapper);
            List<GpCompanyInfo> records = gpCompanyInfoPage.getRecords();
            List<String> codes = records.stream().map(GpCompanyInfo::getStockCode).collect(Collectors.toList());
            System.out.println(JSON.toJSONString(codes));

        }*//*


        // 截取一百条
		Page<GpCompanyInfo> page = new Page<>(1,100);
		page.addOrder(OrderItem.desc("id "));
		Page<GpCompanyInfo> gpCompanyInfoPage = gpCompanyInfoDao.selectPage(page, wrapper);
		List<GpCompanyInfo> records = gpCompanyInfoPage.getRecords();

		LocalDate time = LocalDate.now();
		for(int i = size; i > 0; i--){
		//	int lastSize = ((i) * 100 > count ? count : ((i) * 100));
			List<GpCompanyInfo> gpCompanyInfos = records.subList((i-1) * 100, ((i) * 100 > count ? count : (i) * 100));
			List<String> codes = gpCompanyInfos.stream().map(GpCompanyInfo::getStockCode).collect(Collectors.toList());


			String url = "https://open.lixinger.com/api/cn/company/hot/mm_ha";
			Map<String, String> headers = new HashMap<>();
			JSONObject json = new JSONObject();
			json.put("token","9e26414e-481d-468b-98fe-f765dea84fc0");
			json.put("stockCodes",codes);
			HttpResponse execute = HttpRequest.post(url)
					.body(json.toJSONString()).addHeaders(headers).execute();
			int status = execute.getStatus();
			String ret = execute.body();
			System.out.println(ret);

			GpResponse res = JSON.parseObject(ret,GpResponse.class);
			//ObjectMapper objectMapper = new ObjectMapper();
			//GpResponse<List<GpCompanyInfo>> listResultDTO = objectMapper.readValue(ret, new TypeReference<GpResponse<List<GpCompanyInfo>>>(){});
			Object data = res.getData();
			List<GpCompanyHotMmHaVo> gpCompanyInfosTemp = JSON.parseArray(data.toString(), GpCompanyHotMmHaVo.class);
			gpCompanyInfosTemp.forEach(d -> {
				//	GpCompanyInfo info = JSONObject.parseObject(,GpCompanyInfo.class)
//			String s = JSON.toJSONString(d); //json转换有问题，需要重新转，并指明类型
//			GpCompanyInfo info = JSON.parseObject(s,GpCompanyInfo.class);
				GpCompanyHotMmHa ha = new GpCompanyHotMmHa();
				ha.setCreateTime(time);
				ha.setLastUpdateDate(d.getLast_update_date());
				ha.setMmSh(d.getMm_sh());
				ha.setMmShCapR(d.getMm_sh_cap_r());
				ha.setMmSha(d.getMm_sha());
				ha.setMmShCapRcD1(d.getMm_sh_cap_rc_d1());
				ha.setMmShCapRcD5(d.getMm_sh_cap_rc_d5());
				ha.setMmShCapRcD20(d.getMm_sh_cap_rc_d20());
				ha.setMmShCapRcD60(d.getMm_sh_cap_rc_d60());
				ha.setMmShCapRcD120(d.getMm_sh_cap_rc_d120());
				ha.setMmShcVR(d.getMm_shc_v_r());
				ha.setMmShNbaD1(d.getMm_sh_nba_d1());
				ha.setMmShNbaD20(d.getMm_sh_nba_d20());
				ha.setMmShNbaD60(d.getMm_sh_nba_d60());
				ha.setMmShNbaD120(d.getMm_sh_nba_d120());
				ha.setMmShNbaD5(d.getMm_sh_nba_d5());
				ha.setSpc(d.getSpc());
				ha.setStockCode(d.getStockCode());
				GpCompanyInfo gpCompanyInfo = records.stream().filter(r -> r.getStockCode().equalsIgnoreCase(d.getStockCode())).collect(Collectors.toList()).get(0);
				String name =gpCompanyInfo.getName();
				ha.setName(name);
				gpCompanyHotMmHaDao.insert(ha);
			});

		}


    }


	@Test
    void saveHaTest(){
		String url = "https://open.lixinger.com/api/cn/company/hot/mm_ha";
		Map<String, String> headers = new HashMap<>();
		JSONObject json = new JSONObject();
		json.put("token","9e26414e-481d-468b-98fe-f765dea84fc0");
		List<String> codes = new ArrayList<>();
		codes.add("300124");
		json.put("stockCodes",codes);
		HttpResponse execute = HttpRequest.post(url)
				.body(json.toJSONString()).addHeaders(headers).execute();
		int status = execute.getStatus();
		String ret = execute.body();
		System.out.println(ret);

		GpResponse res = JSON.parseObject(ret,GpResponse.class);
		//ObjectMapper objectMapper = new ObjectMapper();
		//GpResponse<List<GpCompanyInfo>> listResultDTO = objectMapper.readValue(ret, new TypeReference<GpResponse<List<GpCompanyInfo>>>(){});
		Object data = res.getData();
		List<GpCompanyHotMmHaVo> gpCompanyInfosTemp = JSON.parseArray(data.toString(), GpCompanyHotMmHaVo.class);
		System.out.println(JSON.toJSONString(gpCompanyInfosTemp));
		LocalDate now = LocalDate.now();
		gpCompanyInfosTemp.forEach(d -> {
			//	GpCompanyInfo info = JSONObject.parseObject(,GpCompanyInfo.class)
//			String s = JSON.toJSONString(d); //json转换有问题，需要重新转，并指明类型
//			GpCompanyInfo info = JSON.parseObject(s,GpCompanyInfo.class);
			GpCompanyHotMmHa ha = new GpCompanyHotMmHa();
			ha.setCreateTime(now);
			ha.setLastUpdateDate(d.getLast_update_date());
			ha.setMmSh(d.getMm_sh());
			ha.setMmShCapR(d.getMm_sh_cap_r());
			ha.setMmSha(d.getMm_sha());
			ha.setMmShCapRcD1(d.getMm_sh_cap_rc_d1());
			ha.setMmShCapRcD5(d.getMm_sh_cap_rc_d5());
			ha.setMmShCapRcD20(d.getMm_sh_cap_rc_d20());
			ha.setMmShCapRcD60(d.getMm_sh_cap_rc_d60());
			ha.setMmShCapRcD120(d.getMm_sh_cap_rc_d120());
			ha.setMmShcVR(d.getMm_shc_v_r());
			ha.setMmShNbaD1(d.getMm_sh_nba_d1());
			ha.setMmShNbaD20(d.getMm_sh_nba_d20());
			ha.setMmShNbaD60(d.getMm_sh_nba_d60());
			ha.setMmShNbaD120(d.getMm_sh_nba_d120());
			ha.setMmShNbaD5(d.getMm_sh_nba_d5());
			ha.setSpc(d.getSpc());
			ha.setStockCode(d.getStockCode());
			gpCompanyHotMmHaDao.insert(ha);
		});
	}


}
*/
