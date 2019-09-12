package tech.ganyaozi.warframe.stat.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author derek.p.dai at 2019/5/24 15:54
 **/
@Data
public class CetusDTO {

    private String id;

    private Date expiry;

    /**
     * 指示当前是否为白天的字段
     */
    private Boolean isDay;

    @ApiModelProperty(value = "剩余时间",example = "1m 16s")
    private String timeLeft;

    private Boolean isCetus;

    @ApiModelProperty(value = "精简版",example = "1m to Day")
    private String shortString;

}
