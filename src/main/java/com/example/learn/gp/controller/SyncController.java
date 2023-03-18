package com.example.learn.gp.controller;

import com.example.learn.gp.service.SyncGpTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sync")
public class SyncController {

    @Autowired
    SyncGpTask syncGpTask;


    @RequestMapping("/syncBaseInfo")
    @ResponseBody
    public String getGpCompanyHotMmHa(){
        log.info("开始手动同步数据");
        syncGpTask.syncBaseInfo();
        log.info("success开始手动同步数据");
        return "success开始手动同步数据";
    }

    @RequestMapping("/saveMaData")
    @ResponseBody
    public String saveMaData(){
        log.info("开始手动同步互联互通数据");
        syncGpTask.saveMaData();
        log.info("success手动同步互联互通数据");
        return "success手动同步互联互通数据";

    }

}
