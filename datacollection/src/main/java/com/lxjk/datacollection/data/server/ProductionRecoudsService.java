package com.lxjk.datacollection.data.server;

import com.lxjk.datacollection.data.entity.ProductionRecords;


import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/5/21 15:26
 * @Description:
 */

public interface ProductionRecoudsService {

     List<ProductionRecords> selectByTime(String starTime, String endTime) throws IOException;

     String selectMixTime();
}
