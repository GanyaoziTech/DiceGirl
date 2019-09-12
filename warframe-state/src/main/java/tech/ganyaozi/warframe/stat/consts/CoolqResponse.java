package tech.ganyaozi.warframe.stat.consts;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author derek.p.dai at 2019/7/9 10:32
 **/
@Data
@Builder
public class CoolqResponse {



    String reply = "";

    @JsonProperty( "auto_escape")
    @JSONField(name = "auto_escape")
    Boolean autoEscape = true;

    @JsonProperty( "at_sender")
    @JSONField(name = "at_sender")
    Boolean atSender = true;


}
