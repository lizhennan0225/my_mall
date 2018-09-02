package com.lzn.mall.controller.common;

import com.lzn.mall.common.Const;
import com.lzn.mall.utils.CookiesUtil;
import com.lzn.mall.utils.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lzn on 2018/9/2.
 */
public class SessionExpireFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String token = CookiesUtil.readLoginToken(req);
        if (StringUtils.isNotEmpty(token)) {
            String userStr = RedisPoolUtil.get(token);
            if (StringUtils.isNotEmpty(userStr)) {
                RedisPoolUtil.expire(token, Const.Session.SESSION_EXPIRE_TIME);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
