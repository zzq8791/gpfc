package com.example.learn.gp.service;

import com.example.learn.gp.bean.GpCompanyHotMmHa;
import com.example.learn.gp.bean.GpCompanyNonFinancial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DelFifDaysDataTask {

    @Autowired(required=false)
    GpCompanyNonFinancialService gpCompanyNonFinancialService;
    @Autowired(required=false)
    GpCompanyHotMmHaService gpCompanyHotMmHaService;

    @Scheduled(cron="${sync_del_last_fif_data}")
    public void delFifDaysData() {
        log.info("开始删除过去15天的数据");
        LocalDate localDate =  LocalDate.now().minusDays(15);
        List<GpCompanyHotMmHa> gpCompanyHotMmHas = gpCompanyHotMmHaService.getLastFifDays(localDate.toString());
        List<GpCompanyNonFinancial> gpCompanyNonFinancial = gpCompanyNonFinancialService.getLastFifDays(localDate.toString());
        log.info("互联互通条数：{}，非金融条数：{}",gpCompanyHotMmHas.size(),gpCompanyNonFinancial.size());
        gpCompanyHotMmHaService.removeByIds(gpCompanyHotMmHas.stream().map(GpCompanyHotMmHa :: getId).collect(Collectors.toList()));
        gpCompanyNonFinancialService.removeByIds(gpCompanyNonFinancial.stream().map(GpCompanyNonFinancial :: getId).collect(Collectors.toList()));
        log.info("完成删除过去15天的数据");
    }

}
