package tech.ganyaozi.redis;

import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import tech.ganyaozi.utils.StringConsts;

/**
 * @author jv190
 */
public class RedisFactory {

    private String host;
    private String master;
    private String password;
    private Integer database;
    private JedisConnectionFactory connectionFactory;
    private JedisPoolConfig poolConfig;

    public RedisFactory(String host, String master, String password, JedisPoolConfig poolConfig) {
        super();
        this.host = host;
        this.master = master;
        this.password = password;
        this.poolConfig = poolConfig;
        this.init();
    }

    public RedisFactory(String host, String master, String password, Integer database, JedisPoolConfig poolConfig) {
        super();
        this.host = host;
        this.master = master;
        this.password = password;
        this.database = database;
        this.poolConfig = poolConfig;
        this.init();
    }

    StringRedisTemplate createStringRedisTemplate() {
        StringRedisTemplate tpl = new StringRedisTemplate(connectionFactory);
        tpl.afterPropertiesSet();
        return tpl;
    }

    RedisTemplate<String, Object> createObjectRedisTemplate() {
        RedisTemplate<String, Object> tpl = new RedisTemplate<>();
        tpl.setConnectionFactory(connectionFactory);
        tpl.setKeySerializer(new StringRedisSerializer());
        tpl.setHashKeySerializer(new StringRedisSerializer());
        tpl.afterPropertiesSet();
        return tpl;
    }

    RedisTemplate<String, Object> createFastJsonRedisTemplate() {
        RedisTemplate<String, Object> tpl = new RedisTemplate<>();
        tpl.setConnectionFactory(connectionFactory);
        tpl.setKeySerializer(new StringRedisSerializer());
        tpl.setValueSerializer(new RedisJsonSerializer());
        tpl.setHashKeySerializer(new StringRedisSerializer());
        tpl.setHashValueSerializer(new RedisJsonSerializer());
        tpl.afterPropertiesSet();
        return tpl;
    }

    private void init() {
        this.connectionFactory = createJedisConnectionFactory();
    }

    private JedisConnectionFactory createJedisConnectionFactory() {
        JedisConnectionFactory factory;
        factory = new JedisConnectionFactory(createRedisSentinelConfiguration(), poolConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    private RedisSentinelConfiguration createRedisSentinelConfiguration() {
        RedisSentinelConfiguration cfg = new RedisSentinelConfiguration();
        cfg.setMaster(this.master);
        if (password != null) {
            cfg.setPassword(RedisPassword.of(password));
        }
        if (database != null) {
            cfg.setDatabase(database);
        }
        for (String address : this.host.split(StringConsts.COMMA)) {
            String[] hostAndPort = address.split(StringConsts.COLON);
            cfg.addSentinel(new RedisNode(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
        }
        return cfg;
    }

}
