package com.imooc.service.impl;

import com.imooc.enums.SexEnum;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author CAIQIAN04
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    public Sid sid;

    public static final String USER_FACE="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.zhimg.com%2F50%2Fv2-32c17157fd87ee68f17baf016ec86f8d_hd.jpg&refer=http%3A%2F%2Fpic1.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629361059&t=141370518d13449653df9a374fd4754d";
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public boolean qureyUsernameIsExist(String userName) {
        Example example=new Example(Users.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("username",userName);
        Users result=usersMapper.selectOneByExample(example);
        return result==null? false:true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Users createUser(UserBO userBo) throws Exception {
        String userId=sid.nextShort();
        Users user =new Users();
        user.setId(userId);
        user.setUsername(userBo.getUsername());
        user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        user.setNickname(userBo.getUsername());
        user.setFace(USER_FACE);
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setSex(SexEnum.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        usersMapper.insert(user);
        return user;
    }
}
