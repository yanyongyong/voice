package com.lxjk.datacollection.data.controller;

import com.lxjk.datacollection.data.util.ApiUrl;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import static com.lxjk.datacollection.data.util.CommonUtil.isUrlExist;

/**
 * @Author: ziv
 * @Date: 2018/6/1 13:40
 * @Description:定时任务
 */
@Slf4j
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
public class TimedTaskController {

    @Value("${api.datarecerve-url}")
    private String datarecerveUrl;
    @Autowired
    CollectionController collectionController;

    /**
     * @Date: 2018/6/1 13:49
     * @Description: 定时上传更新数据
     */
    @Scheduled(cron = "0/9 * * * * *")
    public void collectionTimeTask(){
        if(isUrlExist(datarecerveUrl)){
            collectionController.detectionDataIsAdd();
        }else {
            log.info("服务器未连接或者本机没有连接互联网");
        }
    }

}
