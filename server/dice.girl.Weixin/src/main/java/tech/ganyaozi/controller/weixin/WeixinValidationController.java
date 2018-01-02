package tech.ganyaozi.controller.weixin;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.ganyaozi.service.DispatcherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com]
 **/
@RequestMapping("/weixin/")
@Controller
public class WeixinValidationController {
    private static final Logger logger = LoggerFactory.getLogger(WeixinValidationController.class);

    private final DispatcherService service;
    private static final String DEFAULT_ENCRYPT = "aes";
    /**
     * 加密的辅助类
     */
    private WXBizMsgCrypt wxBizMsgCrypt;


    @Value("${weixin-token}")
    private String token;
    @Value("${weixin-encoding-aes-key}")
    private String encodingAesKey;
    @Value("${weixin-app-id}")
    private String appId;

    @Autowired
    public WeixinValidationController(DispatcherService service) {
        this.service = service;
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public String verify(HttpServletResponse response, String signature, Long timestamp, String nonce, String echostr) throws IOException, AesException {
        // 将token、timestamp、nonce三个参数进行字典序排序
        if (StringUtils.isBlank(signature)) {
            logger.error("cannot get signature");
            return "cannot get signature";
        }
        if (timestamp == null || timestamp < 0) {
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
        if (wxBizMsgCrypt == null) {
            initWXMsgCrypt();
            if (wxBizMsgCrypt == null) {
                return null;
            }
        }
        if (wxBizMsgCrypt.checkSignature(signature, String.valueOf(timestamp), nonce)) {
            response.getWriter().print(echostr);
        }
        return null;
    }

    private void initWXMsgCrypt() throws AesException {
        wxBizMsgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
    }

    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public String validate(HttpServletRequest request
            , HttpServletResponse response
            , @RequestBody String postData
            , @RequestParam(value = "encrypt_type") String encryptType
            , @RequestParam(value = "msg_signature") String msgSignature
            , String signature
            , String timestamp
            , String nonce) throws AesException {
        if (wxBizMsgCrypt == null) {
            initWXMsgCrypt();
            if (wxBizMsgCrypt == null) {
                return null;
            }
        }
        logger.info("\nraw : \n{} ", postData);
        try (PrintWriter out = response.getWriter()) {
            if (wxBizMsgCrypt.checkSignature(signature, timestamp, nonce)) {
                if (DEFAULT_ENCRYPT.equals(encryptType)) {
                    String xmlText = wxBizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, postData);
                    logger.info(xmlText);
                    String respXml = service.processRequest(xmlText);
                    respXml = wxBizMsgCrypt.encryptMsg(respXml, timestamp, nonce);
                    logger.info(respXml);
                    out.print(respXml);
                } else {
                    String respXml = service.processRequest(postData);
                    out.print(respXml);
                }
            } else {
                out.print("check signature fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
