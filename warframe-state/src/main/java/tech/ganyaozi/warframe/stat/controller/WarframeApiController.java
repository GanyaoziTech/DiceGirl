package tech.ganyaozi.warframe.stat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ganyaozi.warframe.stat.api.WarframeStatApi;
import tech.ganyaozi.warframe.stat.consts.WarframeConst;
import tech.ganyaozi.warframe.stat.dto.FissureDTO;
import tech.ganyaozi.warframe.stat.dto.NightwaveDTO;
import tech.ganyaozi.warframe.stat.dto.SortieDTO;
import tech.ganyaozi.warframe.stat.util.ApiResponse;
import tech.ganyaozi.warframe.stat.util.TranslationDictionary;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static tech.ganyaozi.warframe.stat.util.ApiResponse.newFail;
import static tech.ganyaozi.warframe.stat.util.ApiResponse.newSuccess;

/**
 * @author Derek
 */

@Api(tags = "Warframe 游戏数据")
@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json")
public class WarframeApiController {

    private static final Logger loggerException = LoggerFactory.getLogger(WarframeApiController.class);

    @Resource
    private WarframeStatApi warframeStatApi;
    @Resource
    private TranslationDictionary nightwaveDictionary;
    @Resource
    private TranslationDictionary commonDictionary;
    @Resource
    private TranslationDictionary modifierDictionary;


    @GetMapping("/nightwave")
    @ApiOperation(value = "午夜电波信息. The Current cycle and challenges of Nightwave, a battle-pass-esque rotation and challenge system.", response = NightwaveDTO.class)
    public ApiResponse getNightwaveInfo(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                                        @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONObject resultJson = warframeStatApi.getNightWave(p);

            NightwaveDTO nightWaveDTO = JSON.parseObject(resultJson.toString(), NightwaveDTO.class);

            //翻译挑战的标题和描述
            if (nightWaveDTO.getActiveChallenges() != null) {
                nightWaveDTO.getActiveChallenges().stream()
                        .filter(Objects::nonNull)
                        .forEach(challenge -> {
                            challenge.setDesc(nightwaveDictionary.translate(challenge.getDesc()));
                            challenge.setTitle(nightwaveDictionary.translate(challenge.getTitle()));
                        });
            }
            return ApiResponse.newSuccess(nightWaveDTO);
        } catch (Exception e) {
            loggerException.error("", e);
            return ApiResponse.newFail(e.getMessage());
        }
    }

    @GetMapping("/fissures")
    @ApiOperation(value = "虚空裂缝.", response = FissureDTO.class)
    public ApiResponse getFissures(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                                   @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONArray resultJson = warframeStatApi.getFissures(p);

            List<FissureDTO> list = JSON.parseArray(resultJson.toString(), FissureDTO.class);

            list.stream()
                    .filter(Objects::nonNull)
                    .forEach(fissureDTO -> {
                        try {
                            //星球节点名称
                            String node = fissureDTO.getNode();
                            String planetEN = node.substring(node.indexOf("(") + 1, node.indexOf(")"));
                            fissureDTO.setNode(node.replace(planetEN, commonDictionary.translate(planetEN)));
                            //任务类型
                            fissureDTO.setMissionType(commonDictionary.translate(fissureDTO.getMissionType()));
                        } catch (Exception e) {
                            loggerException.error("", e);
                        }
                    });
            return newSuccess(list);
        } catch (Exception e) {
            loggerException.error("", e);
            return newFail(e.getMessage(), null);
        }
    }

    @GetMapping("/sortie")
    @ApiOperation(value = "每日突击任务", response = SortieDTO.class)
    public ApiResponse getSortie(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                                 @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONObject resultJson = warframeStatApi.getSortie(p);

            SortieDTO sortieDTO = JSON.parseObject(resultJson.toString(), SortieDTO.class);
            if (sortieDTO != null && sortieDTO.getVariants() != null) {
                sortieDTO.getVariants().stream()
                        .filter(Objects::nonNull)
                        .forEach(variant -> {
                            try {
                                //星球节点名称
                                String node = variant.getNode();
                                String planetEN = node.substring(node.indexOf("(") + 1, node.indexOf(")"));
                                variant.setNode(node.replace(planetEN, commonDictionary.translate(planetEN)));
                                //强化类型
                                variant.setModifier(modifierDictionary.translate(variant.getModifier()));
                                //强化类型描述
                                variant.setModifierDescription(modifierDictionary.translate(variant.getModifierDescription()));
                                //任务类型
                                variant.setMissionType(commonDictionary.translate(variant.getMissionType()));
                            } catch (Exception e) {
                                loggerException.error("", e);
                            }
                        });
            }
            return newSuccess(sortieDTO);
        } catch (Exception e) {
            loggerException.error("", e);
            return newFail(e.getMessage(), null);
        }
    }
}
