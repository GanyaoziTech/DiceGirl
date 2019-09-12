package tech.ganyaozi.warframe.stat.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author derek.p.dai at 2019/5/24 17:12
 **/
@Data
@ApiModel
public class NewsDTO {

    String message;
    String link;
    String imageLink;
    Boolean priority;
    @JSONField(format="yyyy-MM-dd hh:mm:ss")
    Date date;
    String eta;
    Boolean update;
    Boolean primeAccess;
    Boolean stream;
    Translations translations;

    @Data
    @ApiModel
    public static class Translations{
        String zh;
    }

}

