package tech.ganyaozi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ganyaozi.bean.message.TextWeixinMessage;
import tech.ganyaozi.bean.qiyu.CommonResult;
import tech.ganyaozi.service.qiyu.QiyuApiService;
import tech.ganyaozi.utils.WechatMessageConvertUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com]
 **/
@Service
public class DispatcherService {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherService.class);

    private final QiyuApiService qiyuApiService;

    @Autowired
    public DispatcherService(QiyuApiService qiyuApiService) {
        this.qiyuApiService = qiyuApiService;
    }

    public String processRequest(String xmlText) {
    // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = WechatMessageConvertUtil.parseXml(xmlText);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextWeixinMessage textMessage = new TextWeixinMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date());
            textMessage.setMsgType(WechatMessageConvertUtil.RESP_MESSAGE_TYPE_TEXT);

            logger.info(" dispatch message : {} ", xmlText);

            switch (msgType) {
                // 文本消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_TEXT:
                    CommonResult result = qiyuApiService.sendTextMessage(fromUserName, xmlText);
                    logger.info("result : {}" ,result);
                    //等待七鱼的返回消息，这里的返回值就写空字符串
                    respContent = "";
                    break;
                // 图片消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_IMAGE:
                    respContent = "您发送的是图片消息！";
                    break;
                // 语音消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_VOICE:
                    respContent = "您发送的是语音消息！";
                    break;
                // 视频消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_VIDEO:
                    respContent = "您发送的是视频消息！";
                    break;
                // 视频消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_SHORTVIDEO:
                    respContent = "您发送的是小视频消息！";
                    break;
                // 地理位置消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_LOCATION:
                    respContent = "您发送的是地理位置消息！";
                    break;
                // 链接消息
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_LINK:
                    respContent = "您发送的是链接消息！";
                    break;
                // 事件推送
                case WechatMessageConvertUtil.REQ_MESSAGE_TYPE_EVENT:
                    respContent = handleEvent(requestMap);
                    break;
                default:
                    break;
            }
            // 设置文本消息的内容
            textMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = WechatMessageConvertUtil.messageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    private String handleEvent(Map<String, String> requestMap) {
        String respContent = null;
        // 事件类型
        String eventType = requestMap.get("Event");
        // 关注
        switch (eventType) {
            case WechatMessageConvertUtil.EVENT_TYPE_SUBSCRIBE:
                respContent = "谢谢您的关注！";
                break;
            // 取消关注
            case WechatMessageConvertUtil.EVENT_TYPE_UNSUBSCRIBE:
                // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                break;
            // 扫描带参数二维码
            case WechatMessageConvertUtil.EVENT_TYPE_SCAN:
                // TODO 处理扫描带参数二维码事件
                break;
            // 上报地理位置
            case WechatMessageConvertUtil.EVENT_TYPE_LOCATION:
                // TODO 处理上报地理位置事件
                break;
            // 自定义菜单
            case WechatMessageConvertUtil.EVENT_TYPE_CLICK:
                // TODO 处理菜单点击事件
                break;
            default:
                break;
        }
        return respContent;
    }
}
