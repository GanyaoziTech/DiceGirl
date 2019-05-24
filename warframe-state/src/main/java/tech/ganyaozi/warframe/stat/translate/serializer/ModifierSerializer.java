package tech.ganyaozi.warframe.stat.translate.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.apache.commons.lang.StringUtils;
import tech.ganyaozi.warframe.stat.translate.TranslationUtils;

import java.lang.reflect.Type;

/**
 * @author derek.p.dai at 2019/5/24 14:06
 **/
public class ModifierSerializer implements ObjectSerializer  {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
        String str = (String) object;
        if (StringUtils.isEmpty(str)) {
            serializer.write("");
            return;
        }
        //星球节点名称
        serializer.write(TranslationUtils.translateModifier(str));
    }
}
