package com.lxjk.datacollection.data.common;

import com.lxjk.datacollection.data.controller.CollectionController;
import com.lxjk.datacollection.data.server.ProductionRecoudsService;
import com.lxjk.datacollection.data.util.ApiUrl;
import com.lxjk.datacollection.data.util.CommonUtil;
import com.lxjk.datacollection.data.util.FileUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.lxjk.datacollection.data.common.FileName.historyReadDBRecord;
import static com.lxjk.datacollection.data.common.FileName.readDBRecord;
import static com.lxjk.datacollection.data.util.CommonUtil.isUrlExist;

/**
 * @Author: ziv
 * @Date: 2018/5/25 08:29
 * @Description: 启动运行，采集到目前时间为止的数据
 */
@Component
@Order(value=1)
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${mylocal.fileSave}")
    private  String fileSavePath;
//    @Value("${mylocal.projectName}")
//    private String projectName;
//    @Value("${mylocal.tenders}")
//    private String tenders;
    @Value("${mylocal.lineNumber}")
    private String lineNumber;
    @Value("${mylocal.configuration}")
    private String configuration;
    @Value("${api.datarecerve-url}")
    private String datarecerveUrl;
    @Autowired
    CommonUtil commonUtil;
    @Autowired
    FileUtil fileUtil;
    @Autowired
    CollectionController collectionController;
    @Autowired
    ApiUrl apiUrl;
    @Autowired
    ProductionRecoudsService productionRecoudsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        fileUtil.saveDataToFile(readDBRecord,format.format(d),true);
        if (configuration.equals("false")||!isUrlExist(datarecerveUrl)){
            return;
        }
        log.info("初始化开始............");
        String minTime = productionRecoudsService.selectMixTime();
        log.info("最早的数据记录时间为："+minTime);
        if (!fileUtil.fileIsExists(historyReadDBRecord)){
            fileUtil.saveDataToFile(historyReadDBRecord,format.format(d),true);
        }
        String one = fileUtil.readLastLine(historyReadDBRecord);
//        if (one != null){
            if(format.parse(one).getTime() <= format.parse(minTime).getTime()){
                return;
            }else {
                while (true){
                    if (loopReads(minTime, format)){ break;}
                }
//            }
        }
    }

    private boolean loopReads(String minTime, DateFormat format) throws Exception {
        String initTime = fileUtil.readLastLine(historyReadDBRecord);
        /**historyReadDBRecord.txt中已经保存了上一次读取到的标志位*/
//        if (initTime != null){
            String starTimes = format.format(format.parse(initTime).getTime() - 10 * 24 * 60 * 60 * 1000);
            fileUtil.saveDataToFile(historyReadDBRecord,starTimes,true);
            if (format.parse(starTimes).getTime() < format.parse(minTime).getTime()){
                if (collectionController.dataSaveFile(starTimes,initTime)){
                    isUploadData(initTime, starTimes);
                }
                return true;
            }
            if (collectionController.dataSaveFile(starTimes,initTime)){
                return !isUploadData(initTime, starTimes);
            }
//        }
//        else {
//            fileUtil.saveDataToFile("historyReadDBRecord.txt",format.format(d),true);
//        }
        return false;
    }

    private Boolean isUploadData(String initTime, String starTimes) throws Exception {
        String projectName = commonUtil.getProperChines("mylocal.projectName");
        String tenders = commonUtil.getProperChines("mylocal.tenders");
        log.info("上传以前数据................");
        return collectionController.uploadData(apiUrl.getApi_uploadData(),fileSavePath+projectName+tenders+lineNumber+starTimes.replace(":","").replace(" ","")+"_"+initTime.replace(":","").replace(" ","")+".txt");
    }

}
