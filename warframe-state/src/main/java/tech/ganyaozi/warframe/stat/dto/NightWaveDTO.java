package tech.ganyaozi.warframe.stat.dto;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 午夜电波pojo
 *
 * @author Derek
 */
@Data
@ApiModel("午夜电波警报")
public class NightWaveDTO {

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

        private String title;

        private String desc;

        private Integer reputation;

    }


}
