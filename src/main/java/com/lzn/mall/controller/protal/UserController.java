package com.lzn.mall.controller.protal;

import com.github.pagehelper.StringUtil;
import com.lzn.mall.common.Const;
import com.lzn.mall.common.ServerResponse;
import com.lzn.mall.pojo.User;
import com.lzn.mall.service.IUserService;
import com.lzn.mall.utils.CookiesUtil;
import com.lzn.mall.utils.JsonUtil;
import com.lzn.mall.utils.RedisPoolUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lzn on 2018/8/29.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(@RequestBody User user, HttpSession session,
                                      HttpServletResponse resp, HttpServletRequest request) {

        String token = CookiesUtil.readLoginToken(request);
        if (token != null) {
            String userStr = RedisPoolUtil.get(token);
            if (userStr != null) {
                user = JsonUtil.string2Obj(userStr, User.class);
                user.setPassword(StringUtils.EMPTY);
                System.out.println("test");
                return ServerResponse.createBySuccessData(user);
            }else {
                RedisPoolUtil.del(token);
                CookiesUtil.delLoginToken(request, resp);
                return ServerResponse.createByErrorMsg("token没有存在于redis中需要重新登录");
            }
        } else {
            ServerResponse<User> response = iUserService.login(user.getUsername(), user.getPassword());
            if (response.isSuccess()) {
                String jsonStr = JsonUtil.obj2PrettyString(response.getData());
                String value = RedisPoolUtil.setex(session.getId(), jsonStr, Const.Session.SESSION_EXPIRE_TIME);
                if (value != null) {
                    CookiesUtil.writeLoginToken(resp, session.getId());
                } else {
                    return ServerResponse.createByErrorMsg("token持久化到redis出错");
                }

                return response;
            }

            return ServerResponse.createByErrorMsg("密码错误");
        }
    }

    @RequestMapping(value = "register_index", method = RequestMethod.GET)
    public String registerIndex() {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(@RequestBody User user) {
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_vailed/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse checkVailed(@PathVariable("username") String username) {

        return iUserService.checkVailed(username);
    }
}
