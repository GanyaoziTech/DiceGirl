package tech.ganyaozi.warframe.stat.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;
import tech.ganyaozi.warframe.stat.consts.CoolqMessage;
import tech.ganyaozi.warframe.stat.consts.CoolqResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Derek
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger loggerException = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"", "home", "index"})
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


    @RequestMapping(value = "robot", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public CoolqResponse readCommand(HttpServletRequest request,
                                     @RequestBody CoolqMessage body) {

        String signature = request.getHeader("X-Signature");

        loggerException.error(" signature : {} ", signature);
        loggerException.error(" CoolqMessage : {} ", body);

        String message = body.getMessage();
        StringBuilder reply = new StringBuilder();
        if (StringUtils.startsWith(message,"/kfc")){
            reply.append("肯德基-黑肥鸡撑死奸亟战 光之肥宅 国服 day#2 ").append("\n");
            reply.append("0%: ").append(" ∞ ").append("\n");
            reply.append("10%: ").append(" 115.41分钟").append("\n");
            reply.append("25%: ").append(" 86.64分钟").append("\n");
            reply.append("50%: ").append(" 45.21分钟").append("\n");
            reply.append("75%: ").append(" 35.12分钟").append("\n");
            reply.append("95%: ").append(" 21.02分钟").append("\n");
            reply.append("99%: ").append(" 14.91分钟").append("\n");
            reply.append("100%: ").append(" 8.99分钟").append("\n");
        }
        return CoolqResponse.builder().reply(reply.toString()).build();

    }
}
