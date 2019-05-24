package tech.ganyaozi.warframe.stat.dto;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import tech.ganyaozi.warframe.stat.translate.serializer.NightwaveSerializer;

import java.util.Date;
import java.util.List;

/**
 * 午夜电波pojo
 *
 * @author Derek
 */
@Data
@ApiModel("午夜电波警报")
public class NightwaveDTO {

    private String id;

    private Date activation;

    private Date expiry;

    private JSONObject params;

    private List<String> rewardTypes;

    private Integer season;

    private String tag;

    private Integer phase;

    private List<NightwaveChallenge> possibleChallenges;

    private List<NightwaveChallenge> activeChallenges;


    @Data
    @ApiModel("挑战内容")
    public static class NightwaveChallenge {

        private String id;

        private Date activation;

        private Date expiry;

        private Boolean isDaily = false;

        private Boolean isElite;

        @JSONField(serializeUsing = NightwaveSerializer.class)
        private String title;

        @JSONField(serializeUsing = NightwaveSerializer.class)
        private String desc;

        private Integer reputation;

    }


}
