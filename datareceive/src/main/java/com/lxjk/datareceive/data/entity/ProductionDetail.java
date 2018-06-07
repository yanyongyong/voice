package com.lxjk.datareceive.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author:zhangyuanming
 * @Date: created in 15:48 2018/5/22
 * @Description:生产明细
 */
@Entity
@Data
@Table(name = "productionDetail")
@ApiModel(description="生产记录明细")
public class ProductionDetail {

    @Id
    @GeneratedValue(generator = "user-uuid")
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @Column(length = 128)
    private String dId;

    @ApiModelProperty("源数据主键")
    private Long id;

    @ApiModelProperty("盘数")
    @Column(name = "diskCount")
    private Integer diskCount;

    @ApiModelProperty("生产时间")
    @Column(name = "produceTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date produceTime;

    @ApiModelProperty("上传时间")
    @Column(name = "uploadTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    @ApiModelProperty("搅拌时间  单位：待确定")
    @Column(name = "stirTime")
    private Integer stirTime;

    @ApiModelProperty(value="生产方量",name="productionVolume")
    @Column(name = "productionVolume",length = 128)
    private String productionVolume;

    @OneToMany
    private List<MaterialVolume> materialVolumes;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiskCount() {
        return diskCount;
    }

    public void setDiskCount(Integer diskCount) {
        this.diskCount = diskCount;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getStirTime() {
        return stirTime;
    }

    public void setStirTime(Integer stirTime) {
        this.stirTime = stirTime;
    }

    public String getProductionVolume() {
        return productionVolume;
    }

    public void setProductionVolume(String productionVolume) {
        this.productionVolume = productionVolume;
    }

    public List<MaterialVolume> getMaterialVolumes() {
        return materialVolumes;
    }

    public void setMaterialVolumes(List<MaterialVolume> materialVolumes) {
        this.materialVolumes = materialVolumes;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String toString() {
        return "ProductionDetail{" +
                "id=" + id +
                ", diskCount=" + diskCount +
                ", produceTime=" + produceTime +
                ", uploadTime=" + uploadTime +
                ", stirTime=" + stirTime +
                ", productionVolume='" + productionVolume + '\'' +
                '}';
    }
}
