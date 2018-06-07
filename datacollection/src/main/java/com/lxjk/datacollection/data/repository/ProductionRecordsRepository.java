package com.lxjk.datacollection.data.repository;


import com.lxjk.datacollection.data.entity.MaterialVolume;
import com.lxjk.datacollection.data.entity.ProdectionDetail;
import com.lxjk.datacollection.data.entity.ProductionRecords;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * @Author: ziv
 * @Date: 2018/5/21 15:21
 * @Description:
 */
@org.springframework.stereotype.Repository
public interface ProductionRecordsRepository{

//    @Select("SELECT ProduceRecordID AS id,ProjectName as projectName,LocationName AS constructionSite,ConcreteGradeName AS designStrength,ProduceQuantity AS productionVolume,ProductLineCode AS lineNumber\n" +
//            "FROM tb_ProduceRecord WHERE #{0} <= CreateDate AND CreateDate < #{1}")
//    List<ProductionRecords> selectByTime(String starTime, String endTime);

    @Select("SELECT ProduceRecordID AS id,LocationName AS constructionSite,ConcreteGradeName AS designStrength,ProduceQuantity AS productionVolume\n" +
            "FROM tb_ProduceRecord WHERE #{0} <= CreateDate AND CreateDate < #{1}")
    List<ProductionRecords> selectByTime(String starTime, String endTime);

    @Select("SELECT BatchFlowID as id,Quantity as productionVolume,BatchID as diskCount,BeginDate_ToMiddle as produceTime\n" +
            "FROM tb_ProduceRecord_Batch WHERE ProduceRecordID = #{produceRecordId}")
    List<ProdectionDetail> selectByProduceRecordID(Integer produceRecordId);

    @Select("SELECT WeightFlowID AS id,MaterielName AS materialName,TargetQuantity AS estimateAmount,RealQuantity AS realAmount,CASE isnull(TargetQuantity, 0) WHEN 0 THEN 100 ELSE CAST ( ( isnull(RealQuantity, 0) - isnull(TargetQuantity, 0) ) * 100 / TargetQuantity AS DECIMAL (8, 2) ) END AS mistake\n" +
            "FROM tb_ProduceRecord_Weight WHERE BatchFlowID = #{batchFlowId}")
    List<MaterialVolume> selectByBatchFlowID(Integer batchFlowId);

    @Select("SELECT TOP 1 CreateDate FROM tb_ProduceRecord ORDER BY CreateDate")
    String selectMixTime();
}
