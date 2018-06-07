package com.lxjk.datareceive.data.service.imp;

import com.lxjk.datareceive.common.Repository;
import com.lxjk.datareceive.common.service.impl.BaseServiceImpl;
import com.lxjk.datareceive.data.entity.ProductionRecords;
import com.lxjk.datareceive.data.repository.ProductionRecordsRepository;
import com.lxjk.datareceive.data.service.ProductionRecoudsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

/**
 * @Author: ziv
 * @Date: 2018/5/21 15:27
 * @Description:业务实现 中间两个方法可弃用
 */
@Service
public class ProductionRecoudsServiceImpl extends BaseServiceImpl<ProductionRecords,String> implements ProductionRecoudsService {

    @Autowired
    ProductionRecordsRepository productionRecordsRepository;

    @Override
    public Page<ProductionRecords> findNoCriteria(Integer page, Integer size) {
        Pageable pageable = of(page, size, Sort.Direction.ASC, "id");
        return productionRecordsRepository.findAll(pageable);
    }

    @Override
    public Page<ProductionRecords> findCriteria(Integer page, Integer size, ProductionRecords productionRecords) {
        Pageable pageable = of(page, size, Sort.Direction.ASC, "id");
        Page<ProductionRecords> bookPage = productionRecordsRepository.findAll(new Specification<ProductionRecords>(){
            @Override
            public Predicate toPredicate(Root<ProductionRecords> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null != productionRecords.getConstructionSite()&&!"".equals(productionRecords.getConstructionSite())){
                    list.add(criteriaBuilder.equal(root.get("constructionSite").as(String.class), productionRecords.getConstructionSite()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return bookPage;
    }

    @Override
    public Repository<ProductionRecords, String> getRepository() {
        return productionRecordsRepository;
    }
}
