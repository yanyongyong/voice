package com.lxjk.datareceive.data.scheduled;

import com.alibaba.fastjson.JSON;
import com.lxjk.datareceive.data.entity.FileProcess;
import com.lxjk.datareceive.data.entity.MaterialVolume;
import com.lxjk.datareceive.data.entity.ProductionDetail;
import com.lxjk.datareceive.data.entity.ProductionRecords;
import com.lxjk.datareceive.data.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/25 9:41
 * @Description:定时任务，定时进行JSON文件解析
 */
@Configuration
@EnableScheduling
public class DataInitServiceImpl{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private FileProcessService fileProcessService;

    @Autowired
    private MaterialVolumeService materialVolumeService;

    @Autowired
    private ProductionRecoudsService productionRecoudsService;

    @Autowired
    private ProductionDetailService productionDetailService;

    //定义一个按一定频率执行的定时任务，每隔30s执行一次，延迟1秒执行
    /**
     * 定时执行数据读取
     */
    @Scheduled(fixedRate = 1000 * 30,initialDelay = 1000)
    public void initData() {
        System.out.printf("定时任务执行:时间" + new Date(System.currentTimeMillis()).toString());
        //查找状态为1的文件
        List<FileProcess> fileProcesses = fileProcessService.findByState(1);
        for(FileProcess fileProcess:fileProcesses){
            if (fileProcess.getState() == 2) {
                //解析成功的不再解析，后面弃用
            } else {
                File file = new File(uploadPath + File.separator + fileProcess.getUploadFileName());
                if (Objects.nonNull(file)) {
                    Long length = file.length();
                    String encoding = "UTF-8";
                    byte[] filecontent = new byte[length.intValue()];
                    String test = "";
                    try {
                        FileInputStream in = new FileInputStream(file);
                        in.read(filecontent);
                        test = new String(filecontent, encoding);
                        int recordsCount = 0;
                        int detailsCount = 0;
                        int volumeCount = 0;
//              JSON文件数据解析
                        List<ProductionRecords> list = JSON.parseArray(test, ProductionRecords.class);
                        recordsCount = list.size();
                        for (ProductionRecords productionRecords : list) {
                            List<ProductionDetail> productionDetails = new ArrayList<>();
                            detailsCount += productionRecords.getProdectionDetails().size();
                            for (ProductionDetail productionDetail : productionRecords.getProdectionDetails()) {
                                List<MaterialVolume> materialVolumes = new ArrayList<>();
                                volumeCount += productionDetail.getMaterialVolumes().size();
                                for (MaterialVolume materialVolume : productionDetail.getMaterialVolumes()) {
                                    MaterialVolume temp = materialVolumeService.add(materialVolume);
                                    materialVolumes.add(temp);
                                }
                                productionDetail.setMaterialVolumes(null);
                                ProductionDetail temp = productionDetailService.add(productionDetail);
                                temp.setMaterialVolumes(materialVolumes);
                                productionDetailService.update(temp);
                                productionDetails.add(temp);
                            }
                            productionRecords.setProdectionDetails(null);
                            ProductionRecords productionRecords1 = productionRecoudsService.add(productionRecords);
                            productionRecords1.setProdectionDetails(productionDetails);
                            productionRecoudsService.update(productionRecords);
                        }
                        fileProcess.setRecordCount(recordsCount);
                        fileProcess.setDetailCount(detailsCount);
                        fileProcess.setVolumeCount(volumeCount);
                        //标记为已处理
                        fileProcess.setState(2);
                        //fileProcessService.update(fileProcess);
                        in.close();
                    } catch (IOException io) {
                        logger.error("IOException");
                        //标记为处理失败
                        fileProcess.setState(4);
//                        fileProcessService.update(fileProcess);
                    }
                    logger.debug(fileProcess.toString() + "解析成功" + test);
                } else {
                    logger.error(fileProcess.toString()+"文件解析失败");
                }
            }
        }
    }
}
