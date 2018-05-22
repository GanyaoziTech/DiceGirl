package tech.ganyaozi.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author jv190
 */
@Service
public class RedisService {

    private StringRedisTemplate stringTmpl;
    private RedisTemplate<String, Object> objectTmpl;
    private RedisTemplate<String, Object> fastJsonTpml;

    RedisService(RedisFactory factory) {
        super();
        this.stringTmpl = factory.createStringRedisTemplate();
        this.objectTmpl = factory.createObjectRedisTemplate();
        this.fastJsonTpml = factory.createFastJsonRedisTemplate();
    }

    public StringRedisTemplate forString() {
        return stringTmpl;
    }

    public RedisTemplate<String, Object> forObject() {
        return objectTmpl;
    }

    public RedisTemplate<String, Object> forFastJson() {
        return fastJsonTpml;
    }

    public boolean lock(String key, int timeout, TimeUnit unit) {
        Boolean locked = forString().opsForValue().setIfAbsent(key, "lock_" + System.currentTimeMillis());
        if (locked != null && locked) {
            Boolean expire = forString().expire(key, timeout, unit);
            if (expire != null && expire) {
                return true;
            } else {
                forString().delete(key);
                return false;
            }
        } else {
            Long expireTime = forString().getExpire(key);
            if (expireTime == null || expireTime == -1) {
                forString().expire(key, timeout, unit);
            }
        }
        return false;
    }

    public void unlock(String key) {
        forString().delete(key);
    }
}
