package tech.ganyaozi.dice.girl.weixin.web.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
@RequestMapping("/weixin/validation/")
@Controller
public class WeixinValidationController {
    @Value("${weixin-token}")
    private String token;

    @Value("${weixin-encoding-aes-key}")
    private String encodingAesKey;

    @RequestMapping("/init")
    @ResponseBody
    public String validate(String signature, Long timestamp, String nonce, String echostr) {
        // 将token、timestamp、nonce三个参数进行字典序排序
        ArrayList<String> paramList = new ArrayList<>(Arrays.asList(signature, timestamp.toString(), nonce));
        Collections.sort(paramList);

        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String raw = StringUtils.join(paramList);
        String sign = DigestUtils.sha1Hex(raw);

        if (StringUtils.equalsIgnoreCase(sign, signature)) {
            return echostr;
        } else {
            return "fail";
        }
    }

}
