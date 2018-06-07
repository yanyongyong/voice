package com.lxjk.datareceive.common;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author:zhangyuanming
 * @Date: created in 2018/5/22 16:23
 * @Description:JPA通用封装
 */
@NoRepositoryBean
public interface Repository<T,ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
