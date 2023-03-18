package com.example.learn.gp.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:config/path.properties")
public class PathDataConfig implements InitializingBean {

    @Value(("${sync_api_cn_company_base_url}"))
    private String baseUrl;
    @Value(("${sync_api_cn_company_hot_mm_ha_url}"))
    private String companyUrl;
    @Value(("${sync_gp_token}"))
    private String token;
    @Value(("${sync_api_cn_company_fundamental_non_financial}"))
    private String fundamentalNonFinancial;
    @Value(("${sync_api_cn_company_api_cn_index}"))
    private String syncApiCnCompanyApiCnIndex;
    @Value(("${sync_api_cn_index_hot_mm_ha_url}"))
    private String syncApiCnIndexHotMmHa;
    @Value(("${sync_api_cn_hy_api_cn_industry_url}"))
    private String sync_api_cn_hy_api_cn_industry;
    @Value(("${sync_api_cn_hy_api_cn_industry_hot_mm_ha_sw_2021_url}"))
    private String  sync_api_cn_hy_api_cn_industry_hot_mm_ha_sw_2021;
    @Value(("${sync_api_cn_hy_api_cn_company_industries_url}"))
    private String  sync_api_cn_hy_api_cn_company_industries;

    public static String stBaseUrl;
    public static String stCompanyMmHaUrl;
    public static String stToken;
    public static String fundamentalNonFinancialUrl;
    public static String syncApiCnCompanyApiCnIndexUrl;
    public static String syncApiCnIndexHotMmHaUrl;
    public static String sync_api_cn_hy_api_cn_industry_url;
    public static String sync_api_cn_hy_api_cn_industry_hot_mm_ha_sw_2021_url;
    public static String sync_api_cn_hy_api_cn_company_industries_url;

    @Override
    public void afterPropertiesSet() throws Exception {
        stBaseUrl = this.baseUrl;
        stCompanyMmHaUrl = this.companyUrl;
        fundamentalNonFinancialUrl = this.fundamentalNonFinancial;
        stToken = this.token;
        syncApiCnCompanyApiCnIndexUrl = this.syncApiCnCompanyApiCnIndex;
        syncApiCnIndexHotMmHaUrl = this.syncApiCnIndexHotMmHa;
        sync_api_cn_hy_api_cn_industry_url = this.sync_api_cn_hy_api_cn_industry;
        sync_api_cn_hy_api_cn_industry_hot_mm_ha_sw_2021_url = this.sync_api_cn_hy_api_cn_industry_hot_mm_ha_sw_2021;
        sync_api_cn_hy_api_cn_company_industries_url = this.sync_api_cn_hy_api_cn_company_industries;
    }
}
