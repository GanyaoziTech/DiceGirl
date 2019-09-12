package tech.ganyaozi.warframe.stat.translate.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.apache.commons.lang.StringUtils;
import tech.ganyaozi.warframe.stat.translate.TranslationUtils;

import java.lang.reflect.Type;

/**
 * @author Derek.P.Dai[derek.dai@corp.netease.com] at 2018/3/6 11:29
 **/
public class CommonSerializer implements ObjectSerializer {

    private static final String STRING_PARAM_NODE = "node";

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {

        if(StringUtils.equalsIgnoreCase((String) fieldName,STRING_PARAM_NODE)) {
            String node = (String) object;
            if (node == null) {
                serializer.write("");
                return;
            }
            //星球节点名称
            String planetEn = node.substring(node.indexOf("(") + 1, node.indexOf(")"));
            serializer.write(node.replace(planetEn, TranslationUtils.translate(TranslationUtils.DictionaryName.COMMON,planetEn)));
        }else{
            String str = (String) object;
            if (StringUtils.isEmpty(str)) {
                serializer.write("");
                return;
            }
            //星球节点名称
            serializer.write(TranslationUtils.translate(TranslationUtils.DictionaryName.COMMON,str));
        }
    }
}
