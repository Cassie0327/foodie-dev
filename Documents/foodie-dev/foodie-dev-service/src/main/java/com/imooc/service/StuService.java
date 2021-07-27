package com.imooc.service;

import com.imooc.pojo.Stu;

/**
 * @author CAIQIAN04
 */
public interface StuService {
    public Stu getStuInfo(int id);
    public void saveStu();
    public void updateStu(int id);
    public void delete(int id);
}
