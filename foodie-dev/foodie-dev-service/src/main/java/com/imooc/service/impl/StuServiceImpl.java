package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void saveStu() {
        Stu stu = new Stu();
        stu.setAge(17);
        stu.setName("jack1");
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setAge(20);
        stu.setName("lucy");
        stu.setId(id);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }
}
