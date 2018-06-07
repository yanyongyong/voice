package com.lxjk.datareceive.data.controller;

import com.alibaba.fastjson.JSON;
import com.lxjk.datareceive.common.response.RetResponse;
import com.lxjk.datareceive.common.response.RetResult;
import com.lxjk.datareceive.data.entity.FileProcess;
import com.lxjk.datareceive.data.entity.MaterialVolume;
import com.lxjk.datareceive.data.entity.ProductionDetail;
import com.lxjk.datareceive.data.entity.ProductionRecords;
import com.lxjk.datareceive.data.service.FileProcessService;
import com.lxjk.datareceive.data.service.MaterialVolumeService;
import com.lxjk.datareceive.data.service.ProductionDetailService;
import com.lxjk.datareceive.data.service.ProductionRecoudsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @Author: ziv
 * @Date: 2018/5/17 10:50
 * @Description:
 */
@Api(tags = "JSON文件采集")
@Controller
@RequestMapping("/test")
public class ReceiveController {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 文件上传路径，在application.yml配置文件中
     */
    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private FileProcessService fileProcessService;
//
//    @Autowired
//    private ProductionDetailService productionDetailService;
//    @Autowired
//    private ProductionRecoudsService productionRecoudsService;
//    @Autowired
//    private MaterialVolumeService materialVolumeService;


    @ApiModelProperty("JSON文件上传Ok")
    @RequestMapping(value = "/jsonUpload",method = RequestMethod.POST)
    @ResponseBody
    public RetResult<String> jsonUpload(HttpServletRequest request,
                                        @RequestParam("file") MultipartFile file) throws Exception{
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = uploadPath;
            //上传文件名
            String filename = file.getOriginalFilename();
            FileProcess fileProcess1 = fileProcessService.findByUploadFileName(filename);
            if (Objects.isNull(fileProcess1)){
                File filepath = new File(path,filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                //将上传文件保存到一个目标文件当中
                file.transferTo(new File(uploadPath + File.separator + filename));
                //添加到数据库中
                FileProcess fileProcess = new FileProcess();
                //设置未处理
                fileProcess.setState(1);
                fileProcess.setUploadFileName(filename);
                fileProcessService.add(fileProcess);

                return RetResponse.makeOKRsp("文件上传成功！");
            } else {
                return RetResponse.makeErrRsp("文件已存在！");
            }
        }else{
            return RetResponse.makeErrRsp("key can  not  be null！");
        }
    }


}
