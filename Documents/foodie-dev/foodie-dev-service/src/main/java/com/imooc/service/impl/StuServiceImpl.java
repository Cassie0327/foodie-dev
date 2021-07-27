package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CAIQIAN04
 */
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id)
    {
        return stuMapper.selectByPrimaryKey(id);
    }
    @Override
    public void saveStu()
    {
        Stu stu=new Stu();
        stu.setName("cassie");
        stu.setAge(17);
        stuMapper.insert(stu);
    }
    @Override
    public void updateStu(int id)
    {
        Stu stu=new Stu();
        stu.setName("jack");
        stu.setAge(28);
        stuMapper.updateByPrimaryKey(stu);

    }
    @Override
    public void delete(int id)
    {

    }
}
