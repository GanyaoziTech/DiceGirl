package tech.ganyaozi.warframe.stat.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author Derek
 */
@Data
@ApiModel
public class FissureDTO {

    private String node;

    private Boolean expired;

    private String eta;

    private String missionType;

    private String tier;

    private String tierNum;

    private String enemy;

    private String id;

    private Date expiry;

    private String activition;

}

