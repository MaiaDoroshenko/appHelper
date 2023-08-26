package com.helpe.YoHelper.service;

import com.helpe.YoHelper.util.SortType;
import org.hibernate.type.SortedMapType;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Size;


public interface CRUDService<RQ,RS,ID>{
    RS create(RQ request);
    RS read (ID id);
    RS update(RQ request,ID id);
    void delete(ID id);
    Page<RS> findAll(Integer page, Integer size, SortType sortType) throws Exception;
}
