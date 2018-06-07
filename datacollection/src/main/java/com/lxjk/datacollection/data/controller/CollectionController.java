package com.lxjk.datacollection.data.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxjk.datacollection.data.entity.ProductionRecords;
import com.lxjk.datacollection.data.entity.User;
import com.lxjk.datacollection.data.server.ProductionRecoudsService;
import com.lxjk.datacollection.data.util.ApiUrl;
import com.lxjk.datacollection.data.util.CommonUtil;
import com.lxjk.datacollection.data.util.FileUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.lxjk.datacollection.data.common.FileName.readDBRecord;

/**
 * @Author: ziv
 * @Date: 2018/5/17 14:50
 * @Description: 采集数据
 */
@Slf4j
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
public class CollectionController {

    @Autowired
    ProductionRecoudsService productionRecoudsService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    CommonUtil commonUtil;
    @Autowired
    FileUtil fileUtil;
    @Autowired
    ApiUrl apiUrl;
    @Value("${mylocal.lineNumber}")
    private String lineNumber;
    private static String token;
    @Value("${api.name}")
    private  String name;
    @Value("${api.password}")
    private  String password;


    /**
     * @Date: 2018/5/28 8:48
     * @Description: 检测数据是否增加，增加则上传
     */
    public void detectionDataIsAdd(){
        log.info("定时扫描是否有数据增加........");
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = format.format(d);
        try {
            String startTime = fileUtil.readLastLine(readDBRecord);
            if (startTime != null){
                if (dataSaveFile(startTime,nowTime)){
                    fileUtil.saveDataToFile(readDBRecord,nowTime,true);
                    log.info("数据有变动，正在上传........");
                    uploadData(apiUrl.getApi_uploadData(),startTime.replace(":","")+"_"+nowTime.replace(":",""));
                }
            }else {
                fileUtil.saveDataToFile(readDBRecord,nowTime,true);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * @Date: 2018/5/24 16:30
     * @Description: 上传数据到服务器
     */
    public Boolean uploadData(String url,String filePath) throws Exception {
        String projectName = commonUtil.getProperChines("mylocal.projectName");
        String tenders = commonUtil.getProperChines("mylocal.tenders");
        RestTemplate rest = new RestTemplate();
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("file", resource);
        param.add("mixingStationName", projectName+tenders+lineNumber);
        log.info("上传到服务器文件名："+filePath);
        try {
            String result = rest.postForObject(url,param,String.class);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String code = String.valueOf(jsonObject.get("code"));
            /**返回结果解析*/
            if (code.equals("400")){
                return false;
            }
            log.info("服务器返回的结果："+result);
        }catch (Exception e){
            log.info(url+"服务器地址出错.........");
            return false;
        }
        return true;
    }

    /**
     * @Date: 2018/5/24 16:40
     * @Description: 数据以json格式保存txt文件
     */
    public Boolean dataSaveFile(String starTime,String endTime) throws ParseException, IOException {
        String projectName = commonUtil.getProperChines("mylocal.projectName");
        String tenders = commonUtil.getProperChines("mylocal.tenders");
        Boolean isData = false;
        List<ProductionRecords> productionRecordsList = productionRecoudsService.selectByTime(starTime,endTime);
        /**判断是否有数据*/
        if(null == productionRecordsList || productionRecordsList.size() ==0 ){

        }else {
            fileUtil.saveDataToFile(projectName+tenders+lineNumber+starTime.replace(":","").replace(" ","")+"_"+endTime.replace(":","").replace(" ","")+".txt",JSON.toJSONString(productionRecordsList),false);
            isData = true;
        }
        return isData;
    }

    @RequestMapping(value = "/collection")
    public void collection(){
        String data = "yan";
        HttpEntity<String> formEntity = new HttpEntity<String>(data, getHttpHeaders());
        try{
            String result = restTemplate.postForObject(apiUrl.getApiURL(),formEntity,String.class);
        }catch (Exception e){
            log.info("token过时，重新登陆ing");
            login();
        }
    }

    @RequestMapping(value = "/login")
    public void login(){
        HttpEntity<User> formEntity = new HttpEntity<User>(new User(name,password), getHttpHeaders());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl.getLoginURL(),formEntity ,String.class);
        JSONObject data = JSON.parseObject(responseEntity.getBody());
        log.info(responseEntity.getBody());
        token = (String) data.get("data");
    }

    /**
     * @Date: 2018/5/21 11:42
     * @Description: 请求头
     */
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        return headers;
    }

}
