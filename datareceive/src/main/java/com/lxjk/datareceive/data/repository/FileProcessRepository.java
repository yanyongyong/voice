package com.lxjk.datareceive.data.repository;

import com.lxjk.datareceive.common.Repository;
import com.lxjk.datareceive.data.entity.FileProcess;

import java.util.List;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/23 8:23
 * @Description:文件处理mapper
 */
public interface FileProcessRepository extends Repository<FileProcess,String> {

    FileProcess findByUploadFileName(String fileName);

    /**
     * 根据处理状态查找文件
     * @param state
     * @return
     */
    List<FileProcess> findByState(int state);
}
