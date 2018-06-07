package com.lxjk.datareceive.data.service;

import com.lxjk.datareceive.common.service.BaseService;
import com.lxjk.datareceive.data.entity.FileProcess;

import java.util.List;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/23 8:51
 * @Description:
 */
public interface FileProcessService extends BaseService<FileProcess,String> {

    FileProcess findByUploadFileName(String fileName);

    List<FileProcess> findByState(int state);
}
