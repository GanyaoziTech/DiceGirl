package tech.ganyaozi.service.qiyu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import tech.ganyaozi.bean.consts.QiyuApiTags;
import tech.ganyaozi.bean.qiyu.*;
import tech.ganyaozi.utils.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 第三方服务器向七鱼服务器发送请求的封装接口。
 *
 * @author jv190
 */
@Service
public class QiyuApiService {

    private static final Logger loggerException = LoggerFactory.getLogger(QiyuApiService.class);

    private static final String VOICE_FILE_TYPE = "amr";

    /**
     * 七鱼API的基本域名
     */
    private static final String HTTPS_QIYU_KF = "https://qiyukf.com/";

    /**
     * 申请客服的接口
     */
    private static final String URL_APPLY_STAFF = "openapi/event/applyStaff";
    private static final String URL_SEND_MESSAGE = "openapi/message/send";
    private static final String URL_UPLOAD_FILE = "openapi/message/uploadFile";
    private static final String URL_SEND_FILE = "openapi/message/sendFile";
    private static final String URL_UPDATE_CRM = "openapi/event/updateUInfo";
    private static final String URL_EVALUATE = "openapi/event/evaluate";
    private static final String URL_QUERY_QUEUE = "openapi/event/queryQueueStatus";


    /**
     * 应用的appKey,可以在七鱼管理后台的设置页面得到
     */
    @Value("${qiyu.appKey}")
    private String appKey;

    /**
     * 应用的appSecret,可以在七鱼管理后台的设置页面得到
     */
    @Value("${qiyu.appSecret}")
    private String appSecret;

    private static String xmlTextContent(Element node, String tagName) {
        return node.getElementsByTagName(tagName).item(0).getTextContent();
    }

    /**
     * 请求分配客服, 返回分配结果
     *
     * @param staffInfo 请求分配客服的信息
     * @return 分配结果
     * @throws IOException e
     */
    public ApplyStaffResult applyStaff(ApplyStaffInfo staffInfo) throws IOException {

        JSONObject res = send(URL_APPLY_STAFF, JSONObject.toJSONString(staffInfo));

        if (res != null) {
            ApplyStaffResult result = new ApplyStaffResult();
            result.setCode(res.getIntValue(QiyuApiTags.CODE));
            result.setMessage(res.getString(QiyuApiTags.MESSAGE));
            if (result.getCode() == HttpStatus.SC_OK) {
                result.setSession(JSONObject.toJavaObject(res, Session.class));
            } else if (result.getCode() == ApplyStaffResult.ERROR_NEED_QUEUE) {
                result.setCount(res.getIntValue(QiyuApiTags.COUNT));
            }
            return result;
        } else {
            loggerException.error("fail to call apply staff !");
        }
        return null;
    }

    /**
     * 发送文本消息给客服
     *
     * @param fromUid 消息来源访客的ID
     * @param content 文本消息内容
     * @return 发送的结果
     * @throws IOException e
     */
    public CommonResult sendTextMessage(String fromUid, String content) throws IOException {
        return sendMessage(fromUid, content, QiyuMessage.TYPE_TEXT);
    }

    /**
     * 发送图片消息给客服。如果没有提供图片宽高，此函数会自动计算，不过为了效率考虑，由外部提供会比较好。
     *
     * @param fromUid 消息来源访客的ID
     * @param path    图片文件的路径
     * @param width   图片的宽
     * @param height  图片的高
     * @return 发送的结果
     * @throws IOException e
     */
    public CommonResult sendImageMessage(String fromUid, String path, int width, int height) throws IOException, NoSuchAlgorithmException {
        String md5 = FileUtil.getMd5(path).toLowerCase();
        long size = FileUtil.getSize(path);
        String url = uploadFile(path, md5);

        if (width <= 0 || height <= 0) {
            int[] imageSize = MediaUtil.querySize(path);
            width = imageSize[0];
            height = imageSize[1];
        }
        return sendImageMessage(fromUid, url, md5, size, width, height);

    }

    /**
     * 发送语音消息
     *
     * @param fromUid  消息来源访客的ID
     * @param path     语音文件的路径
     * @param duration 语音消息长度，如果没有提供，这里也会尝试去计算，不过效率会稍差
     * @return 发送的结果
     * @throws IOException e
     */
    public CommonResult sendAudioMessage(String fromUid, String path, long duration) throws IOException, LineUnavailableException, UnsupportedAudioFileException, NoSuchAlgorithmException {
        String md5 = FileUtil.getMd5(path).toLowerCase();
        long size = FileUtil.getSize(path);
        String url = uploadFile(path, md5);

        if (duration <= 0) {
            duration = MediaUtil.queryAudioDuration(path);
        }

        return sendAudioMessage(fromUid, url, md5, size, duration);
    }

    /**
     * 转发微信的消息给七鱼。
     *
     * @param root        将微信发过来的消息解密之后，解析为一个xml，root是xml的根
     * @param accessToken 对应的微信accessToken
     * @return 返回发送消息的结果。目前只支持文本，语音和图片，其他格式直接返回null
     * @throws IOException 从微信下载文件失败，或者是发给七鱼时失败
     */
    public CommonResult forwardWxMessage(Element root, String accessToken) throws IOException {
        // 这里就直接使用fromUserName作为userId了。
        // 如果同时有多个公众号，又需要区分的话，可以考虑在这里组合上appId一起使用。
        String fromUserName = xmlTextContent(root, "FromUserName");
        String msgType = xmlTextContent(root, "MsgType");

        if (WechatMessageConvertUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
            return sendTextMessage(fromUserName, xmlTextContent(root, "Content"));
        } else if (WechatMessageConvertUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType) || WechatMessageConvertUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)) {
            String mediaId = xmlTextContent(root, "MediaId");
            byte[] buffer = downloadWxFile(mediaId, accessToken);
            if (buffer == null) {
                throw new IOException("download wx file error");
            }
            String md5 = Md5Utils.md5(buffer);
            String base64 = Base64.encodeBase64String(buffer);
            String url = sendFile(md5, base64);

            if (WechatMessageConvertUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) {
                int[] size = MediaUtil.querySize(new ByteArrayInputStream(buffer));
                return sendImageMessage(fromUserName, url, md5, buffer.length, size[0], size[1]);
            } else {
                String format = xmlTextContent(root, "Format");
                long duration;
                if (VOICE_FILE_TYPE.equalsIgnoreCase(format)) {
                    duration = MediaUtil.queryAmrDuration(buffer);
                } else {
                    duration = MediaUtil.queryAudioDuration(new ByteArrayInputStream(buffer));
                }
                return sendAudioMessage(fromUserName, url, md5, buffer.length, duration);
            }
        } else {
            // 七鱼目前还不支持其他类型的消息，不用转发
            loggerException.error("Unsupported file type : {}", msgType);
            return null;
        }
    }

    /**
     * 更新用户的轻量CRM资料
     *
     * @param fromUid 访客用户的uid
     * @param crm     crm资料，必须是JSONArray格式，内容请参照官网文档
     * @return 操作结果
     * @throws IOException e
     */
    public CommonResult updateCrmInfo(String fromUid, JSONArray crm) throws IOException {

        JSONObject json = new JSONObject();
        json.put(QiyuApiTags.UID, fromUid);
        json.put("userinfo", crm);

        JSONObject res = send(URL_UPDATE_CRM, json.toJSONString());
        return JSON.toJavaObject(res, CommonResult.class);
    }

    /**
     * 给指定会话的客服服务进行评价
     *
     * @param fromUid    消息来源访客的ID
     * @param sessionId  会话ID
     * @param evaluation 评价得分。该分数取值应在会话信息的评价模型列表中
     * @return 操作结果
     * @throws IOException e
     */
    public CommonResult evaluate(String fromUid, long sessionId, long evaluation) throws IOException {
        JSONObject json = new JSONObject();
        json.put(QiyuApiTags.UID, fromUid);
        json.put("sessionId", sessionId);
        json.put("evaluation", evaluation);

        JSONObject res = send(URL_EVALUATE, json.toJSONString());
        return JSON.toJavaObject(res, CommonResult.class);
    }

    /**
     * 如果访客当前正在排队中，可调用此接口查询排队状态
     *
     * @param fromUid 消息来源访客的ID
     * @return 操作结果
     * @throws IOException e
     */
    public QueryQueueResult getQueueStatus(String fromUid) throws IOException {
        JSONObject json = new JSONObject();
        json.put(QiyuApiTags.UID, fromUid);

        JSONObject res = send(URL_QUERY_QUEUE, json.toJSONString());
        return JSON.toJavaObject(res, QueryQueueResult.class);
    }

    private CommonResult sendImageMessage(String fromUid, String url, String md5, long size, int width, int height) throws IOException {
        JSONObject image = new JSONObject();
        image.put("url", url);
        image.put("size", size);
        image.put("md5", md5);
        image.put("w", width);
        image.put("h", height);
        return sendMessage(fromUid, image, QiyuMessage.TYPE_PICTURE);
    }

    private CommonResult sendAudioMessage(String fromUid, String url, String md5, long size, long duration) throws IOException {
        JSONObject audio = new JSONObject();
        audio.put("url", url);
        audio.put("size", size);
        audio.put("md5", md5);
        audio.put("dur", duration);
        return sendMessage(fromUid, audio, QiyuMessage.TYPE_AUDIO);
    }

    private CommonResult sendMessage(String fromUid, Object content, String type) throws IOException {
        QiyuMessage message = new QiyuMessage();
        message.setUid(fromUid);
        message.setContent(content);
        message.setMsgType(type);

        JSONObject res = send(URL_SEND_MESSAGE, JSON.toJSONString(message));
        return JSON.toJavaObject(res, CommonResult.class);
    }

    private String uploadFile(String path, String md5) throws IOException {
        String url = HTTPS_QIYU_KF + URL_UPLOAD_FILE;

        long time = System.currentTimeMillis() / 1000;
        String checksum = QiyuPushCheckSum.encode(appSecret, md5, time);

        url = url + "?appKey=" + appKey + "&time=" + time + "&checksum=" + checksum;
        String result = HttpClientPool.getInstance().uploadFile(url, path);
        JSONObject json = JSONObject.parseObject(result);
        return json.getString("url");
    }

    private String sendFile(String path) throws IOException, NoSuchAlgorithmException {
        String md5 = FileUtil.getMd5(path).toLowerCase();
        String base64 = FileUtil.encodeToBase64Binary(path);

        return sendFile(md5, base64);
    }

    ///////////////////////////// 微信转发相关 ///////////////////////////////////

    private String sendFile(String md5, String base64) throws IOException {
        String url = HTTPS_QIYU_KF + URL_SEND_FILE;

        long time = System.currentTimeMillis() / 1000;
        String checksum = QiyuPushCheckSum.encode(appSecret, md5, time);

        url = url + "?appKey=" + appKey + "&time=" + time + "&checksum=" + checksum;
        JSONObject json = HttpClientPool.getInstance().postJson(url, base64);
        return json.getString("url");
    }

    private byte[] downloadWxFile(String mediaId, String accessToken) throws IOException {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s", accessToken, mediaId);
        return HttpClientPool.getInstance().downloadFile(url, 30 * 1000);
    }

    private JSONObject send(String command, String content) throws IOException {
        String url = HTTPS_QIYU_KF + command;

        long time = System.currentTimeMillis() / 1000;
        String md5 = Md5Utils.md5(content);
        String checksum = QiyuPushCheckSum.encode(appSecret, md5, time);
        url = url + "?appKey=" + appKey + "&time=" + time + "&checksum=" + checksum;

        loggerException.info(" post json to qiyu url : {}, content :{}", url,content);

        return HttpClientPool.getInstance().postJson(url, content);
    }
}
