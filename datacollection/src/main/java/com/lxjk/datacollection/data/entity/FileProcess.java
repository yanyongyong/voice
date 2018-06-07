package com.lxjk.datacollection.data.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author:ziv
 * @Date:  2018/5/23 14:50
 * @Description:
 */
@Data
public class FileProcess {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("上传文件名")
    private String uploadFileName;

    @ApiModelProperty("上传时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;

    @ApiModelProperty("状态   1    2   4   分别代表 1未处理 2处理成功4处理失败")
    private int state;

    @ApiModelProperty("生产记录数量（条)")
    private Integer recordCount;

    @ApiModelProperty("生产记录明细数量（条)")
    private Integer detailCount;

    @ApiModelProperty("明细材料用量数量（条)")
    private Integer volumeCount;

    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
