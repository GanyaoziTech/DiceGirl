package tech.ganyaozi.warframe.stat.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.ganyaozi.warframe.stat.consts.WarframeConst;
import tech.ganyaozi.warframe.stat.dto.NightWaveDTO;

import static org.junit.Assert.fail;

/**
 * @author derek.p.dai at 2019/5/17 16:28
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class WarframeStatApiTest {

    private static final Logger loggerException = LoggerFactory.getLogger(WarframeStatApiTest.class);

    private WarframeConst.Platform defaultPlatform = WarframeConst.Platform.PC;

    @Autowired
    private WarframeStatApi warframeStatApi;


    @Test
    public void getAlerts() {
        handleResult(warframeStatApi.getAlerts(defaultPlatform));
    }

    @Test
    public void getCetusInfo() {
        handleResult(warframeStatApi.getCetusInfo(defaultPlatform));
    }

    @Test
    public void getConclaveChallenge() {
        handleResult(warframeStatApi.getConclaveChallenge(defaultPlatform));
    }

    @Test
    public void getConstructionProgress() {
        handleResult(warframeStatApi.getConstructionProgress(defaultPlatform));
    }

    @Test
    public void getDarvoDeals() {
        handleResult(warframeStatApi.getDarvoDeals(defaultPlatform));
    }

    @Test
    public void getFissures() {
        handleResult(warframeStatApi.getFissures(defaultPlatform));
    }

    @Test
    public void getEvents() {
        handleResult(warframeStatApi.getEvents(defaultPlatform));
    }

    @Test
    public void getFlashSales() {
        handleResult(warframeStatApi.getFlashSales(defaultPlatform));

    }


    @Test
    public void getGlobalUpgrades() {
        handleResult(warframeStatApi.getGlobalUpgrades(defaultPlatform));
    }

    @Test
    public void getInvasions() {
        handleResult(warframeStatApi.getInvasions(defaultPlatform));
    }

    @Test
    public void getNews() {
        handleResult(warframeStatApi.getNews(defaultPlatform));
    }

    @Test
    public void getNightWave() {
        JSONObject raw = warframeStatApi.getNightWave(defaultPlatform);
        handleResult(raw);

        NightWaveDTO nightWaveDTO = JSON.parseObject(raw.toString(), NightWaveDTO.class);
        loggerException.info("Before : {} ", nightWaveDTO);
    }

    @Test
    public void getPersistentEnemies() {
        handleResult(warframeStatApi.getPersistentEnemies(defaultPlatform));
    }

    @Test
    public void getVallisCycle() {
        handleResult(warframeStatApi.getVallisCycle(defaultPlatform));
    }

    @Test
    public void getVoidTrader() {
        handleResult(warframeStatApi.getVoidTrader(defaultPlatform));
    }


    private void handleResult(Object result) {
        if (result == null) {
            fail();
        }
        loggerException.info("{}", result);
    }
}
