package tech.ganyaozi.warframe.stat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ganyaozi.warframe.stat.util.TranslationDictionary;

import java.io.IOException;

/**
 * 翻译api获取到信息中的相关短语
 *
 * @author Derek.P.Dai
 */
@Service
public class TranslationService {

    private final TranslationDictionary nightwaveDict;

    @Autowired
    public TranslationService() throws IOException {
        nightwaveDict = new TranslationDictionary("NightWave");
    }

    public String nightwaveTranslate(String nightwaveWord) {
        return nightwaveDict.translate(nightwaveWord);
    }


}
