package com.example.learn.gp.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:config/syncdate.properties")
public class SyncDateConfig {



}
