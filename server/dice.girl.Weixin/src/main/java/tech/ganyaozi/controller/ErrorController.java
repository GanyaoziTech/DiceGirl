package tech.ganyaozi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com]
 * @date 2017/9/6 15:43
 **/
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("/404")
    @ResponseBody
    public String error404() {
        return "/404";
    }

    @RequestMapping("/500")
    @ResponseBody
    public String error500() {
        return "/500";
    }
}
