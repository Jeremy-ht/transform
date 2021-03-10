package com.isoft.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器
 */
public class CommonAutoGenerator {
    public static void main(String[] args) {
        //1.全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)  //是否支持AR模式
                .setIdType(IdType.AUTO)  //主键策略
                .setOutputDir("D:\\IT")  //生成路径
                .setFileOverride(false)  //文件是否覆盖
                .setServiceName("%sService")  //service名称
                .setBaseColumnList(true)  //xml生成column
                .setBaseResultMap(true);  //xml中生成resultmap


        //2.数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://cn1.utools.club:35557/phone?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("root");

        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)//全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)   //数据库表映射到实体的命名策略
                .setInclude("phones")  //====需要生成的表====
//                .setTablePrefix("")  //表前缀
                .setEntityLombokModel(true);  //lombok

        //4.包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.isoft")
                .setController("controller")
                .setEntity("pojo.entity")
                .setService("service")
                .setMapper("dao")
                .setXml("mapper");

        //5.集成
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();

    }
}
