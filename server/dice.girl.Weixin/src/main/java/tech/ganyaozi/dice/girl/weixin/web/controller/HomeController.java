package tech.ganyaozi.dice.girl.weixin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 * @date 2017/9/6 15:37
 **/
@RequestMapping("/home")
@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String home(HttpServletRequest request){
        return "/home";
    }

}
