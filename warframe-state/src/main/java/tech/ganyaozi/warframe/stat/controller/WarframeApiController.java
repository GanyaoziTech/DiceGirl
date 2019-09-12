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
import tech.ganyaozi.warframe.stat.dto.*;
import tech.ganyaozi.warframe.stat.util.ApiResponse;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/")
    @ApiOperation(value = "所有信息", response = AllDTO.class)
    public ApiResponse getAll(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                              @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONObject resultJson = warframeStatApi.getAll(p);
            return newSuccess(JSON.parseObject(resultJson.toJSONString(), AllDTO.class));
        } catch (Exception e) {
            loggerException.error("", e);
            return newFail(e.getMessage(), null);
        }
    }

    @GetMapping("/news")
    @ApiOperation(value = "新闻", response = NewsDTO.class)
    public ApiResponse getNews(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                              @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONArray resultJson = warframeStatApi.getNews(p);
            return newSuccess(JSON.parseArray(resultJson.toJSONString(), NewsDTO.class));
        } catch (Exception e) {
            loggerException.error("", e);
            return newFail(e.getMessage(), null);
        }
    }

    @GetMapping("/nightwave")
    @ApiOperation(value = "午夜电波信息.", response = NightwaveDTO.class)
    public ApiResponse getNightwaveInfo(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                                        @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONObject resultJson = warframeStatApi.getNightWave(p);
            return ApiResponse.newSuccess(JSON.parseObject(resultJson.toString(), NightwaveDTO.class));
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
            List<FissureDTO> list = JSON.parseArray(warframeStatApi.getFissures(p).toString(), FissureDTO.class);
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
            return newSuccess(JSON.parseObject(resultJson.toString(), SortieDTO.class));
        } catch (Exception e) {
            loggerException.error("", e);
            return newFail(e.getMessage(), null);
        }
    }

    @GetMapping("/cetus")
    @ApiOperation(value = "希图斯信息", response = CetusDTO.class)
    public ApiResponse getCetus(@ApiParam(required = true, allowableValues = "pc,xb1,ps4,swi")
                                @RequestParam(defaultValue = "pc") String platform) {
        try {
            WarframeConst.Platform p = WarframeConst.Platform.parse(platform);
            JSONObject resultJson = warframeStatApi.getCetusInfo(p);
            return newSuccess(JSON.parseObject(resultJson.toString(), CetusDTO.class));
        } catch (Exception e) {
            loggerException.error("", e);
            return newFail(e.getMessage(), null);
        }
    }

}
