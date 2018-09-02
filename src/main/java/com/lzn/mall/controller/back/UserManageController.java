package com.lzn.mall.controller.back;

import com.lzn.mall.common.Const;
import com.lzn.mall.common.ServerResponse;
import com.lzn.mall.pojo.User;
import com.lzn.mall.utils.CookiesUtil;
import com.lzn.mall.utils.JsonUtil;
import com.lzn.mall.utils.RedisPoolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lzn on 2018/8/29.
 */
@Controller
@RequestMapping("/user/manage/")
public class UserManageController {
    @RequestMapping("admin")
    public String admin(HttpServletRequest request, ModelAndView modelAndView) {
        String token = CookiesUtil.readLoginToken(request);
        if (token != null) {
            String userStr = RedisPoolUtil.get(token);
            if (userStr != null) {
                User user = JsonUtil.string2Obj(userStr, User.class);
                if (user != null && user.getRole() == Const.USER_ROLE.ADMIN) {
                    return "back/admin";
                }
            }
        }

        return "error";
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookiesUtil.readLoginToken(request);
        if (token != null) {
            Long value = RedisPoolUtil.del(token);
            if (value == 0) {
                return ServerResponse.createBySuccessData("后台异常");
            }
        }
        CookiesUtil.delLoginToken(request, response);

        return ServerResponse.createBySuccessData("退出成功");
    }
}
