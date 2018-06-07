package com.lxjk.datareceive.data.service;

import com.lxjk.datareceive.common.service.BaseService;
import com.lxjk.datareceive.data.entity.ProductionRecords;
import org.springframework.data.domain.Page;

/**
 * @Author: ziv
 * @Date: 2018/5/21 15:26
 * @Description:
 */
public interface ProductionRecoudsService extends BaseService<ProductionRecords,String>{

    Page<ProductionRecords> findNoCriteria(Integer page, Integer size);
    Page<ProductionRecords> findCriteria(Integer page,Integer size,ProductionRecords productionRecords);

}
