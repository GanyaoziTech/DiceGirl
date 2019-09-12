package tech.ganyaozi.warframe.stat.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import tech.ganyaozi.warframe.stat.translate.serializer.CommonSerializer;
import tech.ganyaozi.warframe.stat.translate.serializer.ModifierSerializer;

import java.util.Date;
import java.util.List;

/**
 * @author Derek
 */
@Data
@ApiModel
public class SortieDTO {

    private String id;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date activation;
    private String startString;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
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

        @JSONField(serializeUsing = CommonSerializer.class)
        private String missionType;

        @JSONField(serializeUsing = ModifierSerializer.class)
        private String modifier;

        @JSONField(serializeUsing = ModifierSerializer.class)
        private String modifierDescription;

        @JSONField(serializeUsing = CommonSerializer.class)
        private String node;
    }
}
