package tech.ganyaozi.warframe.stat.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Derek
 */
@Data
@ApiModel
public class SortieDTO {

    private String id;
    private Date activation;
    private String startString;
    private Date expiry;
    private Boolean active;
    private String rewardPool;
    private List<Variants> variants;
    private String boss;
    private String faction;
    private Boolean expired;
    private String eta;

    @Data
    @ApiModel
    public class Variants {
        private String boss;
        private String planet;
        private String missionType;
        private String modifier;
        private String modifierDescription;
        private String node;
    }
}
