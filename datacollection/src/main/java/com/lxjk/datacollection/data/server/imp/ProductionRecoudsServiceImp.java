package com.lxjk.datacollection.data.server.imp;

import com.lxjk.datacollection.data.entity.ProdectionDetail;
import com.lxjk.datacollection.data.entity.ProductionRecords;
import com.lxjk.datacollection.data.repository.ProductionRecordsRepository;
import com.lxjk.datacollection.data.server.ProductionRecoudsService;
import com.lxjk.datacollection.data.util.CommonUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/5/21 15:27
 * @Description:
 */
@Service
public class ProductionRecoudsServiceImp implements ProductionRecoudsService {

    @Autowired
    ProductionRecordsRepository productionRecordsRepository;
    @Value("${mylocal.lineNumber}")
    private String lineNumber;
    @Autowired
    CommonUtil commonUtil;


    @Override
    public List<ProductionRecords> selectByTime(String starTime, String endTime) throws IOException {
        String projectName = commonUtil.getProperChines("mylocal.projectName");
        String tenders = commonUtil.getProperChines("mylocal.tenders");
        List<ProductionRecords> productionRecordsList = productionRecordsRepository.selectByTime(starTime,endTime);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(ProductionRecords prds : productionRecordsList){
            prds.setProjectName(projectName);
            prds.setTenders(tenders);
            prds.setLineNumber(lineNumber);
            List<ProdectionDetail> prodectionDetailList = productionRecordsRepository.selectByProduceRecordID(prds.getId());
            prds.setProdectionDetails(prodectionDetailList);
            for (ProdectionDetail prd : prodectionDetailList){
               prd.setUploadTime(new Date());
               prd.setMaterialVolumes(productionRecordsRepository.selectByBatchFlowID(prd.getId()));
           }
        }
        return productionRecordsList;
    }

    @Override
    public String selectMixTime() {
        return productionRecordsRepository.selectMixTime();
    }


}
