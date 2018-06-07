package com.lxjk.datareceive.data.service.imp;

import com.lxjk.datareceive.common.Repository;
import com.lxjk.datareceive.common.service.impl.BaseServiceImpl;
import com.lxjk.datareceive.data.entity.ProductionDetail;
import com.lxjk.datareceive.data.repository.ProductionDetailRepository;
import com.lxjk.datareceive.data.service.ProductionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/24 17:04
 * @Description:
 */
@Service
public class ProductionDetailServiceImpl extends BaseServiceImpl<ProductionDetail,String> implements ProductionDetailService {

    @Autowired
    ProductionDetailRepository productionDetailRepository;

    @Override
    public Repository<ProductionDetail, String> getRepository() {
        return productionDetailRepository;
    }
}
