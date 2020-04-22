package com.blog.service.impl;

import com.blog.dao.TypeDao;
import com.blog.domain.Type;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Type> findAllByPage(int page, int limit, Type type) {
        Map<String, Object> map = new HashMap<>();
        int begin = (page - 1) * limit;
        map.put("begin", begin);
        map.put("limit", limit);
        map.put("type", type);
        return typeDao.findAllByPage(map);
    }

    @Override
    public Integer findCountsByUid(Type type) {
        return typeDao.findCountsByUid(type);
    }

    @Override
    public void deleteById(Integer tid) {
        typeDao.deleteById(tid);
    }

    @Override
    public void typeUpdate(Type type) {
        typeDao.typeUpdate(type);
    }
}
