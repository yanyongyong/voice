package com.lxjk.datareceive.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/22 16:04
 * @Description:
 */
@Entity
@Data
@Table(name = "materialVolume")
@ApiModel(description="生产记录明细:材料用量")
public class MaterialVolume {

    @Id
    @GeneratedValue(generator = "user-uuid")
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @Column(length = 128)
    private String vId;

    @ApiModelProperty("源数据主键")
    private Long id;

    @ApiModelProperty("材料名称")
    @Column(name = "materialName")
    private String materialName;

    @ApiModelProperty("实际用量（kg）")
    @Column(name = "realAmount",columnDefinition="double(10,2) default '0.00'")
    private Double realAmount;

    @ApiModelProperty("计量用量（kg）")
    @Column(name = "estimateAmount",columnDefinition="double(10,2) default '0.00'")
    private Double estimateAmount;

    @ApiModelProperty("误差  % ")
    @Column(name = "mistake",columnDefinition="double(10,2) default '0.00'")
    private Double mistake;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Double getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(Double estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public Double getMistake() {
        return mistake;
    }

    public void setMistake(Double mistake) {
        this.mistake = mistake;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    @Override
    public String toString() {
        return "MaterialVolume{" +
                "id=" + id +
                ", materialName='" + materialName + '\'' +
                ", realAmount=" + realAmount +
                ", estimateAmount=" + estimateAmount +
                ", mistake=" + mistake +
                '}';
    }
}
