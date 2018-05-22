package tech.ganyaozi.service.qiyu;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.ganyaozi.bean.qiyu.QiyuMessage;
import tech.ganyaozi.bean.qiyu.Session;
import tech.ganyaozi.service.wechat.WechatApiService;
import tech.ganyaozi.utils.AbstractResponseParser;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhoujianghua
 * @date 2016/10/19
 */
@Service
public class QiyuMessageReceiveService extends AbstractResponseParser {

    private final QiyuSessionManager sessionManager;
    private final WechatApiService wechatApiService;

    private ScheduledExecutorService taskManager = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("qiyu-message-receive-pool-%d").daemon(true).build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    public QiyuMessageReceiveService(QiyuSessionManager sessionManager, WechatApiService wechatApiService) {
        this.sessionManager = sessionManager;
        this.wechatApiService = wechatApiService;
    }

    @Override
    protected void onMessage(final QiyuMessage message) {
        taskManager.submit(() -> wechatApiService.replyText(message.getUid(), message.getContent().toString()));
    }

    @Override
    protected void onSessionStart(Session session) {
        sessionManager.onSessionStart(session);
    }

    @Override
    protected void onSessionEnd(final Session session) {
        taskManager.submit(() -> {
            try {
                sessionManager.onSessionEnd(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onValidationError(Long time, String checksum, String eventType, String content) {

    }
}
