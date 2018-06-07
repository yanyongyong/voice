package com.lxjk.datareceive.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/5/21 14:50
 * @Description:
 */
@Entity
@Data
@Table(name = "prodectionRecords")
@ApiModel(description="生产记录")
public class ProductionRecords {

    @Id
    @GeneratedValue(generator = "user-uuid")
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @Column(length = 128)
    private String rId;

    @ApiModelProperty("源数据主键")
    private Long id;

    @ApiModelProperty(value="标段",name="tenders")
    @Column(name = "tenders",length = 128)
    private String tenders;

    @ApiModelProperty(value="生产线编号",name="lineNumber")
    @Column(name = "lineNumber",length = 128)
    private String lineNumber;

    @ApiModelProperty(value="设计强度",name="designStrength")
    @Column(name = "designStrength",length = 128)
    private String designStrength;

    @ApiModelProperty(value="施工部位",name="constructionSite")
    @Column(name = "constructionSite",length = 128)
    private String constructionSite;

    @ApiModelProperty(value="生产方量",name="productionVolume")
    @Column(name = "productionVolume",length = 128)
    private String productionVolume;

    @ApiModelProperty(value="项目名",name="projectName")
    private String projectName;

    @OneToMany
    private List<ProductionDetail> prodectionDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenders() {
        return tenders;
    }

    public void setTenders(String tenders) {
        this.tenders = tenders;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDesignStrength() {
        return designStrength;
    }

    public void setDesignStrength(String designStrength) {
        this.designStrength = designStrength;
    }

    public String getConstructionSite() {
        return constructionSite;
    }

    public void setConstructionSite(String constructionSite) {
        this.constructionSite = constructionSite;
    }

    public String getProductionVolume() {
        return productionVolume;
    }

    public void setProductionVolume(String productionVolume) {
        this.productionVolume = productionVolume;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public List<ProductionDetail> getProdectionDetails() {
        return prodectionDetails;
    }

    public void setProdectionDetails(List<ProductionDetail> prodectionDetails) {
        this.prodectionDetails = prodectionDetails;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        return "ProductionRecords{" +
                "id=" + id +
                ", tenders='" + tenders + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", designStrength='" + designStrength + '\'' +
                ", constructionSite='" + constructionSite + '\'' +
                ", productionVolume='" + productionVolume + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
