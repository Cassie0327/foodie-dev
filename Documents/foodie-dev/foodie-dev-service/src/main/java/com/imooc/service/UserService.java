package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * @author CAIQIAN04
 */

public interface UserService {
    /**
     * 判断用户名是否存在
     * @param userName
     * @return
     */
    public boolean qureyUsernameIsExist(String userName);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    public Users createUser(UserBO userBO) throws Exception;
}
