package com.lzn.mall.controller.protal.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lzn on 2018/8/30.
 */
@Controller
public class IndexController {
    @RequestMapping(value="/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
