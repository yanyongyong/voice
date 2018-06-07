package com.lxjk.datareceive.data.service.imp;

import com.lxjk.datareceive.common.Repository;
import com.lxjk.datareceive.common.service.impl.BaseServiceImpl;
import com.lxjk.datareceive.data.entity.FileProcess;
import com.lxjk.datareceive.data.repository.FileProcessRepository;
import com.lxjk.datareceive.data.service.FileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/23 8:52
 * @Description:
 */
@Service
public class FileProcessServiceImpl extends BaseServiceImpl<FileProcess,String> implements FileProcessService {

    @Autowired
    FileProcessRepository fileProcessRepository;

    @Override
    public Repository<FileProcess, String> getRepository() {
        return fileProcessRepository;
    }

    @Override
    public FileProcess add(FileProcess fileProcess) {
        //设置上传时间
        fileProcess.setUploadDate(new Date(System.currentTimeMillis()));
        //设置更新时间
        fileProcess.setUpdateTime(new Date(System.currentTimeMillis()));
        return super.add(fileProcess);
    }

    @Override
    public void update(FileProcess fileProcess) {
        //设置更新时间
        fileProcess.setUpdateTime(new Date(System.currentTimeMillis()));
        super.update(fileProcess);
    }

    @Override
    public FileProcess findByUploadFileName(String fileName) {
        return fileProcessRepository.findByUploadFileName(fileName);
    }

    @Override
    public List<FileProcess> findByState(int state) {
        return fileProcessRepository.findByState(state);
    }
}
