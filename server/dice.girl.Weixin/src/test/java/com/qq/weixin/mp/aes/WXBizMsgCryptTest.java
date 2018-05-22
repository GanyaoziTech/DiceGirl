package com.qq.weixin.mp.aes;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SuppressWarnings("Duplicates")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/weixin-sevlet.xml"})
public class WXBizMsgCryptTest {

    private String timestamp = "1409304348";
    private String nonce = "xxxxxx";
    private String appId = "wxb11529c136998cb6";
    private String replyMsg = "我是中文abcd123";
    private String xmlFormat = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
    private String afterAesEncrypt = "ZGw32saJhSF0TsqysiTmaVJSQKRg2ID4a9rVNrTfTlCcaamKO7BKxrAH9ANb4BITQR4wW10j8IKJYhuZDh73pQ==";
    private String randomStr = "aaaabbbbccccdddd";
    private String replyMsg2 = "<xml><ToUserName><![CDATA[oia2Tj我是中文jewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";
    private String afterAesEncrypt2 = "ZGw32saJhSF0TsqysiTmaQcZZFJCQ+HoK7LsJAekv/yUtQQavrsiRbKmxwP3GW8EeWpvLQj3mklkQMgnm4fGtYw7T3owI1HpbZXlw3kT/WuFcT/EPgrZSUMu0dN1h3iHWF/B/139MRqwfMfdW2ZPEF1S5ULuPhSqMvvso5ABkMi2KzBgxQP5R/vIhlScux7Ixsnks4DwOnfziC3of/GZxsqbGBu/5JOdre9alLIBmt0lAIvjKK5aB0SSla/VAibQ0gC+xHgAN/Ahkgq6OH+LoDLuoDMFYZ7fNYMtgTrnC+ax8RrS712me8Vf5Z45Z7Z/29qOQQxw3Nr4DPc962s/o5/ASVtvsZNnKcGwSs7tty8CR2Ik4H/GhOb69o1EHTkyIdwDZSHrICyj94M5IvcyrMx3/xH0Wg86Hx6BYRHue1+XS5wTMWWTemQK0KkO/bMe23fVo5nPTeMikwaA4P3O4S+JIwJuvKDTcRLIbYNqkYUvNCPbFxYdhKkGdaN7su6kPNdjGtbmXdAmpO2Cc4kfO1jiJWlAWXb6//wGJGQ+Dol6pT0A3lUMvcu+o3OeBKR4fywDkMxSqBIY573tFo1bauEqKIOhjAWEBwTrAR1Uik8ItZ9AzfQOgHA0HGKcUMm5";
    @Value("${weixin-token}")
    private String token;
    @Value("${weixin-encoding-aes-key}")
    private String encodingAesKey;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void testNormal() throws ParserConfigurationException, SAXException, IOException {
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            String afterEncrpt = pc.encryptMsg(replyMsg, timestamp, nonce);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(afterEncrpt);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");
            NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

            String encrypt = nodelist1.item(0).getTextContent();
            String msgSignature = nodelist2.item(0).getTextContent();
            String fromXML = String.format(xmlFormat, encrypt);

            // 第三方收到公众号平台发送的消息
            String afterDecrpt = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
            assertEquals(replyMsg, afterDecrpt);
        } catch (AesException e) {
            fail("正常流程，怎么就抛出异常了？？？？？？");
        }
    }

    @Test
    @Ignore
    public void testAesEncrypt() {
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            assertEquals(afterAesEncrypt, pc.encrypt(randomStr, replyMsg));
        } catch (AesException e) {
            e.printStackTrace();
            fail("no异常");
        }
    }

    @Test
    public void testAesEncrypt2() {
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            assertEquals(afterAesEncrypt2, pc.encrypt(randomStr, replyMsg2));

        } catch (AesException e) {
            e.printStackTrace();
            fail("no异常");
        }
    }

    @Test
    @Ignore
    public void testIllegalAesKey() {
        try {
            new WXBizMsgCrypt(token, "abcde", appId);
        } catch (AesException e) {
            assertEquals(AesException.IllegalAesKey, e.getCode());
            return;
        }
        fail("错误流程不抛出异常？？？");
    }

    @Test
    @Ignore
    public void testValidateSignatureError() throws ParserConfigurationException, SAXException,
            IOException {
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            String afterEncrpt = pc.encryptMsg(replyMsg, timestamp, nonce);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(afterEncrpt);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");

            String encrypt = nodelist1.item(0).getTextContent();
            String fromXML = String.format(xmlFormat, encrypt);
            pc.decryptMsg("12345", timestamp, nonce, fromXML); // 这里签名错误
        } catch (AesException e) {
            assertEquals(AesException.ValidateSignatureError, e.getCode());
            return;
        }
        fail("错误流程不抛出异常？？？");
    }


}
