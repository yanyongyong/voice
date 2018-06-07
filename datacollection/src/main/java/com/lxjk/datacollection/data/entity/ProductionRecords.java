package com.lxjk.datacollection.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/5/21 14:50
 * @Description:
 */
@Data
@ApiModel(description="生产记录")
public class ProductionRecords {



    private Integer id;

    @ApiModelProperty(value="项目名",name="projectName")
    private String projectName;

    @ApiModelProperty(value="标段",name="tenders")
    private String tenders;

    @ApiModelProperty(value="生产线编号",name="lineNumber")
    private String lineNumber;

    @ApiModelProperty(value="设计强度",name="designStrength")
    private String designStrength;

    @ApiModelProperty(value="施工部位",name="constructionSite")
    private String constructionSite;

    @ApiModelProperty(value="生产方量",name="productionVolume")
    private String productionVolume;


    //    private String proOfMaterialID;
//    @ApiModelProperty(value="生产线编号",name="productLine")
//    private String productLine;
//    @ApiModelProperty(value="施工部位",name="constructionSite")
//    private String constructionSite;
//    @ApiModelProperty(value="设计强度",name="strengthGrade")
//    private String strengthGrade;
//    @ApiModelProperty(value="生产方量",name="volume")
//    private double volume;
//    private String collapse;
//    private String carNumber;
//    private String startTime;
//    private String endTime;

    private List<ProdectionDetail> prodectionDetails;
}
