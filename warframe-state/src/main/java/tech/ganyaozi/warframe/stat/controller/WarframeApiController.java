package tech.ganyaozi.warframe.stat.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ganyaozi.warframe.stat.api.WarframeStatApi;
import tech.ganyaozi.warframe.stat.consts.WarframeConst;
import tech.ganyaozi.warframe.stat.dto.NightWaveDTO;
import tech.ganyaozi.warframe.stat.service.TranslationService;
import tech.ganyaozi.warframe.stat.util.ApiResponse;

/**
 * @author Derek
 */

@Api(tags = "game data")
@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class WarframeApiController {

    private static final Logger loggerException = LoggerFactory.getLogger(WarframeApiController.class);

    private final WarframeStatApi warframeStatApi;
    private final TranslationService translationService;

    @Autowired
    public WarframeApiController(WarframeStatApi warframeStatApi, TranslationService translationService) {
        this.warframeStatApi = warframeStatApi;
        this.translationService = translationService;
    }


    @GetMapping("/nightwave")
    @ApiOperation(value = "午夜电波信息. The Current cycle and challenges of Nightwave, a battle-pass-esque rotation and challenge system.", response = NightWaveDTO.class)
    public ApiResponse getNightwaveInfo(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                                        @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            NightWaveDTO nightWaveDTO = JSON.parseObject(warframeStatApi.getNightWave(p).toString(), NightWaveDTO.class);

            //翻译挑战的标题和描述
            nightWaveDTO.getActiveChallenges().forEach(challenge -> {
                challenge.setDesc(translationService.nightwaveTranslate(challenge.getDesc()));
                challenge.setTitle(translationService.nightwaveTranslate(challenge.getTitle()));
            });
            return ApiResponse.newSuccess(nightWaveDTO);
        } catch (Exception e) {
            loggerException.error("", e);
            return ApiResponse.newFail(e.getMessage());
        }

    }
}
