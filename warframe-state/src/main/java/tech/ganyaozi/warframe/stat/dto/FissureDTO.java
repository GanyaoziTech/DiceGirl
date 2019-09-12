package tech.ganyaozi.warframe.stat.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import tech.ganyaozi.warframe.stat.translate.serializer.CommonSerializer;

import java.util.Date;

/**
 * @author Derek
 */
@Data
@ApiModel
public class FissureDTO {

    @JSONField(serializeUsing = CommonSerializer.class)
    private String node;

    private Boolean expired;

    private String eta;

    @JSONField(serializeUsing = CommonSerializer.class)
    private String missionType;

    private String tier;

    private String tierNum;

    private String enemy;

    private String id;

    private Date expiry;

    private String activation;

}

