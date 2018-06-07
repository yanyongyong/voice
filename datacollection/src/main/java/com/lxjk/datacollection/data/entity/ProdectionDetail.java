package com.lxjk.datacollection.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/23 14:50
 * @Description:生产明细
 */
@Data
@ApiModel(description="生产记录明细")
public class ProdectionDetail {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("盘数")
    private Integer diskCount;

    @ApiModelProperty("生产时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date produceTime;

    @ApiModelProperty("上传时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

//    @ApiModelProperty("搅拌时间  单位：待确定")
//    private Integer stirTime;
//    @ApiModelProperty(value="生产方量",name="productionVolume")
//    private String productionVolume;
//    private String proOfMaterialID;
//    private String recipeID;
//    private String warningID;
//    private String recipeName;
//    private Recipe recipe;
//    private String mixingStationID;
//    private String mixingStationName;
//    private double volume;
//    private String endTime;
//    private String startTime;

    private List<MaterialVolume> materialVolumes;
}
