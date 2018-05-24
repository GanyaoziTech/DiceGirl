package tech.ganyaozi.controller.weixin;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.ganyaozi.service.DispatcherService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com]
 **/
@RequestMapping("/wechat/")
@Controller
public class WeixinValidationController {
    private static final Logger logger = LoggerFactory.getLogger(WeixinValidationController.class);
    private static final String DEFAULT_ENCRYPT = "aes";
    private final DispatcherService service;
    /**
     * 加密的辅助类
     */
    private WXBizMsgCrypt wxBizMsgCrypt;


    @Value("${wechat-token}")
    private String token;
    @Value("${wechat-encoding-aes-key}")
    private String encodingAesKey;
    @Value("${wechat-app-id}")
    private String appId;

    @Autowired
    public WeixinValidationController(DispatcherService service) {
        this.service = service;
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public String verify(HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException, AesException {

        logger.info("signature : {} \n timestamp : {} \nnonce : {} \nechostr : {} \n ", signature, timestamp, nonce, echostr);

        // 将token、timestamp、nonce三个参数进行字典序排序
        if (StringUtils.isBlank(signature)) {
            logger.error("cannot get signature");
            return "cannot get signature";
        }
        if (StringUtils.isEmpty(timestamp) || !StringUtils.isNumeric(timestamp)) {
            logger.error("cannot get timestamp");
            return "cannot get timestamp";
        }
        if (StringUtils.isBlank(nonce)) {
            logger.error("cannot get nonce");
            return "cannot get nonce";
        }
        if (StringUtils.isBlank(signature)) {
            logger.error("cannot get signature");
            return "cannot get signature";
        }
        if (StringUtils.isBlank(echostr)) {
            logger.error("cannot get echostr");
            return "cannot get echostr";
        }
        if (checkSignature(signature, timestamp, nonce)) {
            response.getWriter().print(echostr);
        }
        return null;
    }

    private void initWXMsgCrypt() throws AesException {
        if (StringUtils.isEmpty(encodingAesKey) || StringUtils.isEmpty(token)) {
            logger.error("try to init WXMsgCrypt, but encodingAesKey is : {}, and token is {}", encodingAesKey, token);
            return;
        }
        wxBizMsgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
    }

    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public String validate(HttpServletResponse response
            , @RequestBody String postData
            , @RequestParam(value = "encrypt_type", required = false) String encryptType
            , @RequestParam(value = "msg_signature", required = false) String msgSignature
            , String signature
            , String timestamp
            , String nonce) {
        logger.info("\nraw : \n{} ", postData);
        try (PrintWriter out = response.getWriter()) {
            if (checkSignature(signature, timestamp, nonce)) {
                if (StringUtils.equals(DEFAULT_ENCRYPT,encryptType)) {
                    //内容加密，调用解密模块
                    //初始化解密器
                    if (wxBizMsgCrypt == null) {
                        initWXMsgCrypt();
                        if (wxBizMsgCrypt == null) {
                            //微信服务器要求，必须对所有消息进行反馈，即使是处理失败。
                            //此处返回success，使得聊天内容不会报错。
                            out.print("success");
                            logger.error("try to decrypt message , but fail to innit WxBizMsgCrypt");
                            return null;
                        }
                    }
                    String xmlText = wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, postData);
                    logger.info("xmlText: {}", xmlText);
                    String respXml = service.processRequest(xmlText);
                    //对空白消息的特殊处理，直接返回空字符串
                    if (respXml == null) {
                        out.print("success");
                    }
                    respXml = wxBizMsgCrypt.encryptMsg(respXml, timestamp, nonce);
                    logger.error("response : {}", respXml);
                    out.print(respXml);
                } else {
                    logger.info("encrypt_type : {} ", encryptType);
                    String respXml = service.processRequest(postData);
                    //对空白消息的特殊处理，直接返回空字符串
                    if (respXml == null) {
                        out.print("success");
                        return null;
                    }
                    out.print(respXml);
                }
            } else {
                logger.error("check signature fail");
                out.print("check signature fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查签名是否正确
     *
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @throws AesException 密钥初始化时抛出
     */
    private boolean checkSignature(String signature, String timestamp, String nonce) throws AesException {
        if (wxBizMsgCrypt == null) {
            initWXMsgCrypt();
            if (wxBizMsgCrypt == null) {
                // 未加密模式
                logger.info("receive check signature message, but encodingAes key is null ,try to use plain text mode");
                if (StringUtils.equals(signature, SHA1.getSHA1(token, String.valueOf(timestamp), nonce))) {
                    return true;
                } else {
                    logger.error("check signature fail !");
                    return false;
                }
            }
        }
        //加密模式
        return wxBizMsgCrypt.checkSignature(signature, String.valueOf(timestamp), nonce);
    }


}
