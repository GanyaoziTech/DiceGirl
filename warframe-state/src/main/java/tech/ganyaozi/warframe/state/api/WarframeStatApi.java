package tech.ganyaozi.warframe.state.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import tech.ganyaozi.warframe.state.consts.WarframeConst;
import tech.ganyaozi.warframe.state.util.OkHttpUtil;

/**
 * Warframe Stat from <a href='https://api.warframestate.us/'>warframe state</a>
 *
 * @author derek.p.dai at 2019/5/17 15:43
 * @see <a href='https://docs.warframestate.us'>warframe state docs</a>
 **/
@Setter
@Service
@ConfigurationProperties("warframe-stat.api")
public class WarframeStatApi {

    private static final Logger loggerException = LoggerFactory.getLogger(WarframeStatApi.class);

    private final WarframeConst consts;

    private String alerts;
    private String cetus;
    private String conclave;
    private String constructionProgress;
    private String darvo;
    private String events;
    private String fissures;
    private String flashSales;

    @Autowired
    public WarframeStatApi(WarframeConst consts) {
        this.consts = consts;
    }

    /**
     * 警报信息
     * Description and rewards for Alerts, such as lotus's gift
     *
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONArray getAlerts(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, alerts);
    }

    /**
     * 希图斯信息
     * Data on the Day/night cycle for Cetus on earth
     *
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONObject getCetusInfo(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, cetus);
    }

    /**
     * 武形秘仪挑战
     * <p>
     * Get Conclave Challenge Data , including day/night and expire time
     *
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONArray getConclaveChallenge(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, conclave);
    }

    /**
     * 巨人战舰和利刃豺狼舰队的建造进度
     * <p>
     * Construction percentages for showing how far constructed the enemy fleets are.
     *
     * @return null if fail
     */
    @Nullable
    public JSONObject getConstructionProgress(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, constructionProgress);
    }

    /**
     * Darvo的每日优惠 Darvo's Daily Deal details
     *
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONArray getDarvoDeals(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, darvo);
    }

    /**
     * 虚空裂缝信息 Information about current Void Fissure missions.
     *
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONArray getFissures(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, fissures);
    }

    /**
     * 活动信息，比如巨人战舰活动，Events, such as Fomorian Attacks are included here.
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONArray getEvents(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, events);
    }

    /**
     * Darvo的热门商品 Popular Deals, discounts, featured deals..
     * @param platform platform
     * @return null if fail
     */
    @Nullable
    public JSONArray getFlashSales(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, flashSales);
    }



    private JSONArray sendGetAsJsonArray(WarframeConst.Platform platform, String path) {
        return OkHttpUtil.sendGetAsJsonArray(consts.domain + platform.getAlias() + path);
    }

    private JSONObject sendGetAsJsonObject(WarframeConst.Platform platform, String path) {
        return OkHttpUtil.sendGetAsJsonObject(consts.domain + platform.getAlias() + path);
    }
}


