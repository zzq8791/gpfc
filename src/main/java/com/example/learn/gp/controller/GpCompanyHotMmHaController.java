package com.example.learn.gp.controller;


import com.alibaba.fastjson.JSON;
import com.example.learn.gp.bean.GpCompanyHotMmHa;
import com.example.learn.gp.param.GpParam;
import com.example.learn.gp.service.GpCompanyHotMmHaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzq
 * @since 2022-09-12
 */
@Slf4j
@RestController
@RequestMapping("/gpCompanyHotMmHa")
public class GpCompanyHotMmHaController {

    @Autowired(required=false)
    GpCompanyHotMmHaService gpCompanyHotMmHaService;

    @RequestMapping("/getInfo")
    public GpCompanyHotMmHa getGpCompanyHotMmHa(@RequestBody GpParam param){
        log.info("id : {}", JSON.toJSONString(param.getId()));
        GpCompanyHotMmHa gpCompanyHotMmHa = gpCompanyHotMmHaService.byId(param.getId());
      //  log.info("info : {}", JSON.toJSONString(gpCompanyHotMmHa));
        return gpCompanyHotMmHa;
    }

}

