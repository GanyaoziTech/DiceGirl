package tech.ganyaozi.service.qiyu;

import org.springframework.stereotype.Service;
import tech.ganyaozi.bean.qiyu.Session;
import tech.ganyaozi.service.wechat.WechatApiService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujianghua
 * @date 2016/10/20
 */
@Service
public class QiyuSessionManager {

    @Resource
    private WechatApiService wechatApiService;
    @Resource
    private QiyuApiService qiyuApiService;

    /**
     * 这里测试用，只会有一个节点，所以放一个map就行了
     */
    private Map<String, Session> sessions = new HashMap<>();

    /**
     * 缓存待评价的会话
     */
    private Map<String, Session> waitingEvaluation = new HashMap<>();

    public void onSessionStart(Session session) {
        sessions.put(session.getUid(), session);
        waitingEvaluation.remove(session.getUid());
    }

    public void onSessionEnd(Session session) {
        Session cache = sessions.get(session.getUid());
        if (cache != null && cache.getEvaluationModel() != null) {
            // 发评价
            try {
                wechatApiService.replyText(cache.getUid(), evaluationString(cache.getEvaluationModel()));
                waitingEvaluation.put(cache.getUid(), cache);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sessions.remove(session.getUid());
    }

    public boolean isEvaluationMsg(String openId, String textMsg) {
        Session session = waitingEvaluation.get(openId);
        if (session == null || session.getEvaluationModel() == null) {
            return false;
        }

        try {
            int index = Integer.parseInt(textMsg);
            List<Session.SatisfactionEntry> entries = session.getEvaluationModel().getList();
            if (index > 0 && index <= entries.size()) {
                Session.SatisfactionEntry entry = entries.get(index - 1);
                qiyuApiService.evaluate(openId, session.getSessionId(), entry.getValue());
                waitingEvaluation.remove(session.getUid());

                wechatApiService.replyText(openId, "谢谢，您的评价为：" + entry.getName() + "！");
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public boolean isInSession(String openId) {
        return sessions.containsKey(openId);
    }

    private String evaluationString(Session.SatisfactionSetting setting) {
        StringBuilder sb = new StringBuilder();
        sb.append("请输入对应的数字给我们评价：").append("\r\n");
        List<Session.SatisfactionEntry> entries = setting.getList();
        for (int i = 1; i <= entries.size(); ++i) {
            sb.append(i).append(". ").append(entries.get(i - 1).getName()).append("\r\n");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
