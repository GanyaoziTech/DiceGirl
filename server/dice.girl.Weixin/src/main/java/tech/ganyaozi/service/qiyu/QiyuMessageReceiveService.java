package tech.ganyaozi.service.qiyu;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.ganyaozi.bean.qiyu.QiyuMessage;
import tech.ganyaozi.bean.qiyu.Session;
import tech.ganyaozi.utils.AbstractResponseParser;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhoujianghua
 * @date 2016/10/19
 */
@Component("qiyuMessageReceiver")
public class QiyuMessageReceiveService extends AbstractResponseParser {

    private final QiyuSessionManager sessionManager;

    private ScheduledExecutorService taskManager = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("qiyu-message-receive-pool-%d").daemon(true).build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    public QiyuMessageReceiveService(QiyuSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    protected void onMessage(final QiyuMessage message) {
        taskManager.submit(() -> {
            try {
                wxMessageService.replyText(message.getUid(), message.getContent().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
