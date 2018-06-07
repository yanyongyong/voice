package com.lxjk.datacollection.data.controller;

import com.lxjk.datacollection.data.common.MyApplicationRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.lxjk.datacollection.config.ChangeSpringConfig.updateProperties;

/**
 * @Author: ziv
 * @Date: 2018/5/31 10:37
 * @Description:
 */
@Controller
public class ProperController {

    @Value("${api.datarecerve-url}")
    private String datarecerveUrl;
    @Value("${mylocal.fileSave}")
    private  String fileSavePath;
    @Value("${api.name}")
    private  String name;
    @Value("${api.password}")
    private  String password;

    @RequestMapping("/")
    public String redirectLiveVideo() {
        return "/configuration";
    }

    @RequestMapping(value = "/cofigs")
    @ResponseBody
    public String collection(String projectName,String tenders,String lineNumber,String dbType,String dbName,String dbPassword,String userName){
        Map<String, String> app = new HashMap<>();
        app.put("file-encoding","UTF-8");
        app.put("spring.thymeleaf.prefix","classpath:/templates/");
        app.put("spring.thymeleaf.suffix",".html");
        app.put("spring.thymeleaf.prefix","classpath:/templates/");
        app.put("spring.thymeleaf.mode","HTML5");
        app.put("spring.thymeleaf.encoding","UTF-8");
        app.put("api.datarecerve-url",datarecerveUrl);
        app.put("mylocal.fileSave",fileSavePath);
        app.put("api.name",name);
        app.put("api.password",password);
        app.put("mylocal.tenders",tenders);
        app.put("mylocal.projectName",projectName);
        app.put("mylocal.lineNumber",lineNumber);
        if (dbType.equals("sqlserver")){
            app.put("spring.datasource.url","jdbc:sqlserver://localhost:1433;DatabaseName="+"NewConcrete_JY");
            app.put("spring.datasource.driver-class-name","com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            app.put("spring.datasource.type","com.alibaba.druid.pool.DruidDataSource");
        }
        if (dbType.equals("access")){
            app.put("spring.datasource.driverClassName","net.ucanaccess.jdbc.UcanaccessDriver");
            app.put("spring.datasource.url","dbc:ucanaccess://"+dbName);
        }
        app.put("spring.datasource.username",userName);
        app.put("spring.datasource.password",dbPassword);
        app.put("mylocal.tenders",tenders);
        app.put("mylocal.projectName",projectName);
        app.put("mylocal.lineNumber",lineNumber);
        app.put("mylocal.configuration","true");
        if (dbName.equals("issqlserver")){
            app.put("spring.datasource.url",lineNumber);
            app.put("spring.datasource.driver-class-name",lineNumber);
            app.put("spring.datasource.type",lineNumber);
        }
        app.put("spring.datasource.username",dbName);
        app.put("spring.datasource.password",dbPassword);
        try {
            updateProperties("application.properties",app);
        }catch (Exception e){
            return "false";
        }
        return "true";
    }



}
