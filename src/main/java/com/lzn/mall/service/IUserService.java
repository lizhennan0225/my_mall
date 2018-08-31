package com.lzn.mall.service;

import com.lzn.mall.common.ServerResponse;
import com.lzn.mall.pojo.User;

/**
 * Created by lzn on 2018/8/29.
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse checkVailed(String username);

    ServerResponse register(User user);
}
