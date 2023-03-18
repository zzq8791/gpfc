package com.example.learn.tools;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis-plus代码生成器
 */
public class GeneratorServiceEntity {
    /**
     * 作者署名
     */
    private static final String AUTHOR = "zzq";


    /**
     * 数据库连接URL
     */
//    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/teacher_ai_test?useSSL=false";
    
    private static final String DB_URL = "jdbc:mysql://42.193.103.111:3306/gp_data?serverTimezone=UTC";
    
    
    /**
     * 数据库连接用户名
     */
    private static final String USERNAME = "root";

    /**
     * 数据库连接密码
     */
//    private static final String PASSWORD = "Ab123456";
    private static final String PASSWORD = "zzQ123&^%";
    /**
     * 数据库连接驱动
     */
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    /**
     * 数据库类型
     */
    private static final DbType DB_TYPE = DbType.MYSQL;

    /**
     * 代码文件生成路径
     */
    private static final String OUTPUT_DIR = "D:\\workspace\\logcode2";

    /**
     * 生成文件所在包名
     */
    private static final String PACKAGE_NAME = "com.example.learn.tools";

    private static final String SUPER_SERVICE_IMPL = "com.eduServer.common.service.BaseService";
    private static final String SUPER_SERVICE = "com.eduServer.common.service.IBaseService";
//    private static final String SUPER_SERVICE = "";
    
    /**
     * WEB层超类
     */
    private static final String SUPER_CONTROLLER = "";

    /**
     * 实体类超类
     */
    private static final String SUPER_ENTITY = "com.eduServer.common.entity.BaseEntity";
//    private static final String SUPER_ENTITY = "";


    public void generateCode(String[] tables) {
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, PACKAGE_NAME, tables);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DB_TYPE)
                .setUrl(DB_URL)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setDriverName(DRIVER_NAME);
        
     // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://192.168.185.61:3306/data_windows?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("puser");
//        dsc.setPassword("1y7b57");
        
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setSuperControllerClass(SUPER_CONTROLLER)
                .setRestControllerStyle(false)
                //.setSuperEntityClass(SUPER_ENTITY)
               // .setSuperServiceImplClass(SUPER_SERVICE_IMPL)
               // .setSuperServiceClass(SUPER_SERVICE)
                .setCapitalMode(true)
                .setEntityLombokModel(true)
//                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
//                .setTablePrefix("q_")   //要移除的前缀
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setAuthor(AUTHOR)
                .setOutputDir(OUTPUT_DIR)
                .setActiveRecord(true)
                .setFileOverride(true)
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        config.setMapperName("%sDao");
        config.setXmlName("%sMapper");
//        config.setEntityName("%sPO");
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(config)
            .setDataSource(dataSourceConfig)
            .setStrategy(strategyConfig)
            .setPackageInfo(
                    new PackageConfig()
                            .setParent(packageName)
                            .setController("controller")
                            .setEntity("po")
                            .setMapper("dao")
                            .setXml("mapping")
            )
            .execute();
    }

}

