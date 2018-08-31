package com.lzn.mall.service.impl;

import com.lzn.mall.common.ServerResponse;
import com.lzn.mall.dao.UserMapper;
import com.lzn.mall.pojo.User;
import com.lzn.mall.service.IUserService;
import com.lzn.mall.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lzn on 2018/8/29.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {

        int count = userMapper.selectByUsername(username);
        if (count == 0){
            return ServerResponse.createByErrorMsg("该用户不存在");
        }

        String MD5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectByUsernamePassword(username, MD5Password);
        if (user == null){
            return ServerResponse.createByErrorMsg("密码错误！");
        }

        user.setPassword(StringUtils.EMPTY);





        return ServerResponse.createBySuccessData(user);
    }

    @Override
    public ServerResponse checkVailed(String username) {

        int count = userMapper.selectByUsername(username);
        if (count != 0){
            return ServerResponse.createByErrorMsg("该用户名已存在");
        }

        return ServerResponse.createBySuccessData("该用户名可以使用");
    }

    @Override
    public ServerResponse register(User user) {

        String passwrod = user.getPassword();
        String md5Password = MD5Util.MD5EncodeUtf8(passwrod);
        user.setPassword(md5Password);

        int count = userMapper.insert(user);
        if (count == 0 ){
            return  ServerResponse.createByErrorMsg("注册失败");
        }
        return ServerResponse.createBySuccessData("注册成功");
    }
}
