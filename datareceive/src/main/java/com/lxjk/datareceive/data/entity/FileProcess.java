package com.lxjk.datareceive.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author:zhangyuanming
 * @Date: created in 20180522 15:44
 * @Description:
 */
@Entity
@Data
@Table(name = "fileProcess")
@ApiModel(description="文件处理记录")
public class FileProcess {

    //使用hibernate uuid主键生成策略
    @Id
    @GeneratedValue(generator = "user-uuid")
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @Column(length = 128)
    private String id;

    @ApiModelProperty("上传文件名")
    @Column(name = "uploadFileName",length = 255)
    private String uploadFileName;


    @ApiModelProperty("上传时间")
    @Column(name="uploadDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;

    @ApiModelProperty("状态   1    2   4   分别代表 1未处理 2处理成功4处理失败")
    @Column(name="state")
    private int state;

    @ApiModelProperty("生产记录数量（条)")
    @Column(name="recordCount")
    private Integer recordCount;

    @ApiModelProperty("生产记录明细数量（条)")
    @Column(name="detailCount")
    private Integer detailCount;

    @ApiModelProperty("明细材料用量数量（条)")
    @Column(name="volumeCount")
    private Integer volumeCount;

    @ApiModelProperty("更新时间")
    @Column(name = "updateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }

    public Integer getVolumeCount() {
        return volumeCount;
    }

    public void setVolumeCount(Integer volumeCount) {
        this.volumeCount = volumeCount;
    }

    @Override
    public String toString() {
        return "FileProcess{" +
                "id=" + id +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", uploadDate=" + uploadDate +
                ", state=" + state +
                ", recordCount=" + recordCount +
                ", detailCount=" + detailCount +
                ", volumeCount=" + volumeCount +
                ", updateTime=" + updateTime +
                '}';
    }
}
