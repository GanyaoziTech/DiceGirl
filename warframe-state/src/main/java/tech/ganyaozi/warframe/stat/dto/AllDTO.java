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
public class AllDTO {

    @JSONField(format="yyyy-MM-dd hh:mm:ss")
    private Date timestamp;

    private NewsDTO[] news;

    private SortieDTO sortie;

    private FissureDTO[] fissures;

    private CetusDTO cetusCycle;
    
}
