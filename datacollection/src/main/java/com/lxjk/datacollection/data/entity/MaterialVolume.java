package com.lxjk.datacollection.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author:ziv
 * @Date: 2018/5/23 14:50
 * @Description:
 */
@Data
@ApiModel(description="生产记录明细:材料用量")
public class MaterialVolume {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("材料名称")
    private String materialName;

    @ApiModelProperty("实际用量（kg）")
    private Double realAmount;

    @ApiModelProperty("计量用量（kg）")
    private Double estimateAmount;

    @ApiModelProperty("误差  % ")
    private Double mistake;

//    private String proOfMaterialID;
//    private String materialID;
//    @ApiModelProperty("材料名称")
//    private String materialName;
//    @ApiModelProperty("实际用量（kg）")
//    private double consumptionVal ;
//    @ApiModelProperty("实际用量（kg）")
//    private double targetConVal ;
//    @ApiModelProperty("误差  % ")
//    private double recipeVal ;

}
