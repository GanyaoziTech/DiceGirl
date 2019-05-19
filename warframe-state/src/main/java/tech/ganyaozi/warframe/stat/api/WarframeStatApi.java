package tech.ganyaozi.warframe.stat.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import tech.ganyaozi.warframe.stat.consts.WarframeConst;
import tech.ganyaozi.warframe.stat.util.OkHttpUtil;

/**
 * TODO add redis as cache
 * <p>
 * Warframe Stat from <a href='https://api.warframestat.us/'>warframe stat</a>
 *
 * @author derek.p.dai at 2019/5/17 15:43
 * @see <a href='https://docs.warframestat.us'>warframe stat docs</a>
 **/
@Setter
@Service
@ConfigurationProperties(prefix = "warframe-stat.api")
public class WarframeStatApi {

    private static final Logger loggerException = LoggerFactory.getLogger(WarframeStatApi.class);

    private final WarframeConst constant;

    private String alerts;
    private String cetus;
    private String conclave;
    private String constructionProgress;
    private String dailyDeals;
    private String events;
    private String fissures;
    private String flashSales;
    private String globalUpgrades;
    private String invasions;
    private String news;
    private String nightwave;
    private String persistentEnemies;
    private String vallisCycle;
    private String voidTrader;

    @Autowired
    public WarframeStatApi(WarframeConst constant) {
        this.constant = constant;
    }

    /**
     * 警报信息
     * Description and rewards for Alerts, such as lotus's gift
     *
     * @param platform platform
     */
    public JSONArray getAlerts(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, alerts);
    }

    /**
     * 希图斯信息
     * Data on the Day/night cycle for Cetus on earth
     *
     * @param platform platform
     */
    public JSONObject getCetusInfo(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, cetus);
    }

    /**
     * 武形秘仪挑战
     * <p>
     * Get Conclave Challenge Data , including day/night and expire time
     *
     * @param platform platform
     */
    public JSONArray getConclaveChallenge(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, conclave);
    }

    /**
     * 巨人战舰和利刃豺狼舰队的建造进度
     * <p>
     * Construction percentages for showing how far constructed the enemy fleets are.
     */
    public JSONObject getConstructionProgress(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, constructionProgress);
    }

    /**
     * Darvo的每日优惠 Darvo's Daily Deal details
     *
     * @param platform platform
     */
    public JSONArray getDarvoDeals(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, dailyDeals);
    }

    /**
     * 虚空裂缝信息 Information about current Void Fissure missions.
     *
     * @param platform platform
     */
    public JSONArray getFissures(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, fissures);
    }

    /**
     * 活动信息，比如巨人战舰活动，Events, such as Fomorian Attacks are included here.
     *
     * @param platform platform
     */
    public JSONArray getEvents(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, events);
    }

    /**
     * Darvo的热门商品 Popular Deals, discounts, featured deals..
     *
     * @param platform platform
     */
    public JSONArray getFlashSales(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, flashSales);
    }

    /**
     * 官方的双倍奖励信息
     * Any current modifiers applied to all users, such as double drops.
     *
     * @param platform platform
     */
    public JSONArray getGlobalUpgrades(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, globalUpgrades);
    }

    /**
     * 入侵信息
     * Data on invasion missions, such as estimated completion time, rewards, etc.
     *
     * @param platform platform
     */
    public JSONArray getInvasions(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, invasions);
    }

    /**
     * 新闻内容(多语言)
     * Translated News items from the worldstate
     *
     * @param platform platform
     */
    public JSONArray getNews(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, news);
    }

    /**
     * 午夜电波信息
     * The Current cycle and challenges of Nightwave, a battle-pass-esque rotation and challenge system
     *
     * @param platform platform
     */
    public JSONObject getNightWave(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, nightwave);
    }

    /**
     * Data about current acolytes attacking the Sol System
     *
     * @param platform platform
     */
    public JSONArray getPersistentEnemies(WarframeConst.Platform platform) {
        return sendGetAsJsonArray(platform, persistentEnemies);
    }

    /**
     * 奥布山谷当前的寒冷和温暖循环
     * The current cycle of the Orb Vallis warm/cold cycle
     *
     * @param platform platform
     */
    public JSONObject getVallisCycle(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, vallisCycle);
    }

    /**
     * 虚空奸商的商品，或者还有多久到达
     * <p>
     * Information on the current Void Trader offerings, or when he will arrive.
     *
     * @param platform platform
     */
    public JSONObject getVoidTrader(WarframeConst.Platform platform) {
        return sendGetAsJsonObject(platform, voidTrader);
    }

    private JSONArray sendGetAsJsonArray(WarframeConst.Platform platform, String path) {
        try {
            return OkHttpUtil.sendGetAsJsonArray(constant.domain + platform.getAlias() + path);
        } catch (Exception e) {
            loggerException.error("", e);
        }
        return new JSONArray();
    }

    private JSONObject sendGetAsJsonObject(WarframeConst.Platform platform, String path) {
        try {
            return OkHttpUtil.sendGetAsJsonObject(constant.domain + platform.getAlias() + path);
        } catch (Exception e) {
            loggerException.error("", e);
        }
        return new JSONObject();
    }
}


