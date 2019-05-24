package tech.ganyaozi.warframe.stat.translate;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 * @author derek.p.dai at 2019/5/24 10:02
 **/
public class TranslationUtils {

    private static TranslationDictionary alertDic;
    private static TranslationDictionary commonDic;
    private static TranslationDictionary invasionDic;
    private static TranslationDictionary modifierDic;
    private static TranslationDictionary nightwaveDic;
    private static TranslationDictionary relicDic;
    private static TranslationDictionary rivenDic;
    private static TranslationDictionary saleDic;

    static {
        try {
            alertDic = new TranslationDictionary("Alert");
            commonDic = new TranslationDictionary("Dict");
            invasionDic = new TranslationDictionary("Invasion");
            modifierDic = new TranslationDictionary("Modifier");
            nightwaveDic = new TranslationDictionary("NightWave");
            relicDic = new TranslationDictionary("Relic");
            rivenDic = new TranslationDictionary("Riven");
            saleDic = new TranslationDictionary("sale");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String translateNightwave(String origin){
        if (StringUtils.isEmpty(origin)){
            return origin;
        }
        return nightwaveDic.translate(origin);
    }

    public static String translateCommon(String origin){
        return commonDic.translate(origin);
    }

    public static String translateModifier(String origin){
        return modifierDic.translate(origin);
    }


}
