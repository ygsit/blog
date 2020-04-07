package com.blog.service.impl;

import com.blog.dao.TypeDao;
import com.blog.domain.Type;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> findAll() {
        return typeDao.findAll();
    }

    @Override
    public int findCounts() {
        return typeDao.findCounts();
    }

    @Override
    public int findNameCount(String name) {
        return typeDao.findNameCount(name);
    }

    @Override
    public List<Type> findOnePage() {
        return typeDao.findOnePage();
    }

    @Override
    public void typeAdd(Type type) {
        typeDao.typeAdd(type);
    }

    @Override
    public Integer typeNameIsExist(String name) {
        return typeDao.typeNameIsExist(name);
    }
}
