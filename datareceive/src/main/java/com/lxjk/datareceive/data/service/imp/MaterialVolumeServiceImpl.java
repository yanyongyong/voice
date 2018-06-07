package com.lxjk.datareceive.data.service.imp;

import com.lxjk.datareceive.common.Repository;
import com.lxjk.datareceive.common.service.impl.BaseServiceImpl;
import com.lxjk.datareceive.data.entity.MaterialVolume;
import com.lxjk.datareceive.data.repository.MaterialVolumeRepository;
import com.lxjk.datareceive.data.service.MaterialVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/24 17:08
 * @Description:
 */
@Service
public class MaterialVolumeServiceImpl extends BaseServiceImpl<MaterialVolume,String> implements MaterialVolumeService {

    @Autowired
    MaterialVolumeRepository materialVolumeRepository;

    @Override
    public Repository<MaterialVolume, String> getRepository() {
        return materialVolumeRepository;
    }
}
