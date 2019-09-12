package tech.ganyaozi.warframe.stat.consts;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author derek.p.dai at 2019/7/9 10:36
 **/
@Data
public class CoolqMessage {

    @JSONField(name = "post_type")
    String postType;

    Long time;

    @JSONField(name = "self_id")
    Long selfId;

    @JSONField(name = "group_id")
    Long groupId;

    @JSONField(name = "message_type")
    String messageType;

    @JSONField(name = "sub_type")
    String subType;

    @JSONField(name = "message_id")
    Long messageId;

    @JSONField(name = "user_id")
    Long userId;

    @JSONField(name = "raw_message")
    String rawMessage;

    String message;

    Integer font;

    @Valid CoolqSender sender;


    @Data
    public static class CoolqSender {

        String nickname;

        @JSONField(name = "user_id")
        Long userId;

        String card;

        String sex;

        Integer age;
    }

}
