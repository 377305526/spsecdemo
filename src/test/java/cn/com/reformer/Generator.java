package cn.com.reformer;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/07/12/16:23
 * @Description:
 */

public class Generator {
    public static void main(String[] args) {
        //1.创建generator对象
        AutoGenerator autoGenerator = new AutoGenerator();


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        autoGenerator.setDataSource(dsc);


        //指定生成的包位置
        String packageName = "generator";
        //指定生成文件的绝对路径

        //3.全局配置(指明这些类生成的具体位置以及作者....)
        GlobalConfig globalConfig = new GlobalConfig();
        System.out.println(System.getProperty("user.dir"));
        String propertyPath = System.getProperty("user.dir");

//        globalConfig.setOutputDir(propertyPath+ "/" + packageName + "/src/main/java");
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setOpen(false);//不打开文件

        //设置作者名称
        globalConfig.setAuthor("Nipppppp");
        autoGenerator.setGlobalConfig(globalConfig);


        //4.设置包信息(生成的类放在哪个包里面)
        PackageConfig packageConfig = new PackageConfig();

        packageConfig.setParent("cn.com.reformer");

        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper");


        Map<String, String> pathInfoMap = new HashMap<>();
        String entityPath = "E:\\keyfreeplus\\keyfreeplus\\src\\main\\java\\cn\\com\\reformer\\entity";

        String mapper_path = "E:\\keyfreeplus\\keyfreeplus\\src\\main\\java\\cn\\com\\reformer\\mapper";

        String xml_path = "E:\\keyfreeplus\\keyfreeplus\\src\\main\\resources\\mapper";

        String service_path =
                "E:\\keyfreeplus\\keyfreeplus\\src\\main\\java\\cn\\com\\reformer\\service";


        String service_impl_path = "E:\\keyfreeplus\\keyfreeplus\\src\\main\\java\\cn\\com\\reformer\\service\\impl";

        pathInfoMap.put("entity_path", entityPath);
        pathInfoMap.put("mapper_path", mapper_path);
        pathInfoMap.put("xml_path", xml_path);
        pathInfoMap.put("service_path", service_path);
        pathInfoMap.put("service_impl_path", service_impl_path);
        packageConfig.setPathInfo(pathInfoMap);
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        autoGenerator.setCfg(cfg);


        //5.配置策略
        StrategyConfig strategyConfig = new StrategyConfig();

        //提供lombok
        strategyConfig.setEntityLombokModel(true);
        //支持驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置需要添加的表名称
        strategyConfig.setInclude("admin_permission", "permission", "plus_admin");
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        //6.执行
        autoGenerator.execute();
    }
}
