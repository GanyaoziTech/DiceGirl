package tech.ganyaozi.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis的FastJson序列化实现。
 * 
 * @author derek.dai
 */
public class RedisJsonSerializer implements RedisSerializer<Object> {

    private final StringRedisSerializer serializer = new StringRedisSerializer();

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        return serializer.serialize(JSON.toJSONString(object));
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return JSON.parse(serializer.deserialize(bytes));
    }

    public static <T> T toJavaObject(Object obj, Class<T> classOfT) {
        try {
            if (obj instanceof JSON) {
                return JSON.toJavaObject((JSON) obj, classOfT);
            }
        } catch (Exception e) {
            throw new RuntimeException("not support " + obj.getClass().getName() + " to " + classOfT.getName(), e);
        }
        throw new RuntimeException("not support " + obj.getClass().getName() + " to " + classOfT.getName());
    }

    public static <T> List<T> toJavaObjectList(Iterable<?> iterable, Class<T> classOfT) {
        List<T> list = new ArrayList<>();
        for (Object object : iterable) {
            list.add(toJavaObject(object, classOfT));
        }
        return list;
    }
}
