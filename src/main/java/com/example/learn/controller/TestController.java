package com.example.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.learn.core.anno.SessionData;
import com.example.learn.core.bean.Testvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/")
@Controller
public class TestController {

	/*@RequestMapping("/test")
	@ResponseBody
	public TestParam getStr(@RequestBody TestParam str , @SessionData Testvo vo){
		log.info("进入controller");
		// ResponseEnum.LICENCE_NOT_FOUND.assertNotNull(null,"ccc");
		log.info("进入controller str: {}",str);
		return str;
	}*/
	
	@RequestMapping(value = "/testGt",method = RequestMethod.GET)
	@ResponseBody
	public String getStrGt(@RequestBody String str){
		log.info("进入controller");
		// ResponseEnum.LICENCE_NOT_FOUND.assertNotNull(null,"ccc");
		log.info("进入controller str: {}",str);
		return str;
	}
	
}
