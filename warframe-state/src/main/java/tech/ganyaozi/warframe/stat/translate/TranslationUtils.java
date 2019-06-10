package tech.ganyaozi.warframe.stat.translate;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author derek.p.dai at 2019/5/24 10:02
 **/
public class TranslationUtils {

    private static final Logger loggerException = LoggerFactory.getLogger(TranslationUtils.class);
    private static Map<DictionaryName, TranslationDictionary> dictionaryMap = new HashMap<>();

    static {
        Stream.of(DictionaryName.values()).forEach(dictionaryName -> {
            try {
                dictionaryMap.put(dictionaryName, new TranslationDictionary(dictionaryName.name));
            } catch (IOException e) {
                loggerException.error("", e);
            }
        });
    }

    public static String translate(DictionaryName dicName, String origin) {
        if (StringUtils.isEmpty(origin)) {
            return origin;
        }
        TranslationDictionary dic = dictionaryMap.get(dicName);
        if (dic == null) {
            return origin;
        }
        return dic.translate(origin);
    }

    public enum DictionaryName {
        /**
         * 警报词典
         */
        ALERT("Alert"),
        /**
         * 通用词典
         */
        COMMON("Dict"),
        /**
         * 入侵词典
         */
        INVASION("Invasion"),
        /**
         * 强化词典
         */
        MODIFIER("Modifier"),
        /**
         * 午夜电波词典
         */
        NIGHT_WAVE("NightWave"),
        /**
         * 虚空遗物
         */
        RELIC("Relic"),
        /**
         * 紫卡词典
         */
        RIVEN("Riven"),
        /**
         * 每日特惠词典
         */
        SALE("Sale");
        private String name;

        DictionaryName(String name) {
            this.name = name;
        }
    }


}
