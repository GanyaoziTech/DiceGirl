package tech.ganyaozi.warframe.state.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.ganyaozi.warframe.state.consts.WarframeConst;

import static org.junit.Assert.fail;

/**
 * @author derek.p.dai at 2019/5/17 16:28
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class WarframeStatApiTest {

    private static final Logger loggerException = LoggerFactory.getLogger(WarframeStatApiTest.class);

    @Autowired
    private WarframeStatApi warframeStatApi;


    @Test
    public void getAlerts() {
        handleResult(  warframeStatApi.getAlerts(WarframeConst.Platform.PC));
    }

    @Test
    public void getCetusInfo() {
        handleResult(  warframeStatApi.getCetusInfo(WarframeConst.Platform.PC));
    }

    @Test
    public void getConclaveChallenge() {
        handleResult( warframeStatApi.getConclaveChallenge(WarframeConst.Platform.PC));
    }

    @Test
    public void getConstructionProgress() {
        handleResult(  warframeStatApi.getConstructionProgress(WarframeConst.Platform.PC));
    }

    @Test
    public void getDarvoDeals() {
        handleResult(  warframeStatApi.getDarvoDeals(WarframeConst.Platform.PC));
    }

    @Test
    public void getFissures() {
        handleResult( warframeStatApi.getFissures(WarframeConst.Platform.PC));
    }

    @Test
    public void getEvents() {
        handleResult(  warframeStatApi.getEvents(WarframeConst.Platform.PC));
    }


    @Test
    public void getFlashSales() {
        handleResult( warframeStatApi.getFlashSales(WarframeConst.Platform.PC));

    }

    private void handleResult(Object result){
        if(result==null){
            fail();
        }
        loggerException.info("{}" , result);
    }

}
