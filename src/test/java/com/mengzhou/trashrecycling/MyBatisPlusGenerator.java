package com.mengzhou.trashrecycling;


import java.sql.SQLException;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * MyBatisPlus代码生成器
 *
 * @author ZHOUTONG
 * @date 2019年08月04日 21:21
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) throws SQLException {
        String projectPath = System.getProperty("user.dir");

        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true).setAuthor("CC") // 作者
                .setOutputDir(projectPath + "/src/main/java") // 生成路径
                .setFileOverride(true)
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService") // 设置生成的service接口的名字的首字母是否为I
                // IEmployeeService
                .setBaseResultMap(true)// 生成基本的resultMap
                .setBaseColumnList(true);// 生成基本的SQL片段

        // 2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL) // 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver").setUrl("jdbc:mysql://127.0.0.1:3306/recycling?serverTimezone=CTT&autoReconnect=true&useSSL=false").setUsername("root")
                .setPassword("123456");

        // 3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setDbColumnUnderline(true) // 指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude("integraldetails"); // 生成的表.为空的话默认生成所有

        // 4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.mengzhou.trashrecycling").setMapper("mapper")// dao
                .setService("service")// servcie
                .setController("controller")// controller
                .setEntity("model").setXml("mapper");// mapper.xml

        // 5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig).setPackageInfo(pkConfig);

        // 6. 执行
        ag.execute();
    }

}
