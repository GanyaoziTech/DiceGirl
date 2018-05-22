package tech.ganyaozi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.ganyaozi.service.qiyu.QiyuMessageReceiveService;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com] at 2018/5/22 10:39
 **/
@Controller
@RequestMapping("/qiyu")
public class QiyuController {

    private static final Logger logger = LoggerFactory.getLogger(QiyuController.class);

    private final QiyuMessageReceiveService qiyuMessageReceiveService;

    @Autowired
    public QiyuController(QiyuMessageReceiveService qiyuMessageReceiveService) {
        this.qiyuMessageReceiveService = qiyuMessageReceiveService;
    }

    /**
     * 处理来自七鱼的HTTP请求
     *
     * @param time      time stamp
     * @param checksum  sum
     * @param eventType event type
     * @param postData  data
     * @return empty string
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public String onUnicornMsg(@RequestParam(value = "time") Long time,
                               @RequestParam(value = "checksum") String checksum,
                               @RequestParam(value = "eventType") String eventType,
                               @RequestBody String postData) {
        try {
            logger.debug("receive qiyu: " + eventType + " content: " + postData);
            return qiyuMessageReceiveService.onReceive(time, checksum, eventType, postData);
        } catch (Throwable e) {
            logger.warn("onUnicornMsg error: " + e);
        }
        return "";
    }
}
