package tech.ganyaozi.warframe.stat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tech.ganyaozi.warframe.stat.api.WarframeStatApi;

import java.io.IOException;

/**
 * 翻译api获取到信息中的相关短语
 *
 * @author Derek.P.Dai
 */
@Service
public class TranslationService {

    private static final Logger loggerException = LoggerFactory.getLogger(TranslationService.class);
    private static final Logger loggerBusiness = LoggerFactory.getLogger("business");

    private final WarframeStatApi warframeStatApi;

    private final TranslationDictionary nightwaveDict;

    @Autowired
    public TranslationService(ResourceLoader resourceLoader, WarframeStatApi warframeStatApi) throws IOException {
        this.warframeStatApi = warframeStatApi;
        nightwaveDict = new TranslationDictionary("NightWave", resourceLoader);
    }


    public String nightwaveTranslate(String nightwaveWord) {
        return nightwaveDict.translate(nightwaveWord);
    }


}
