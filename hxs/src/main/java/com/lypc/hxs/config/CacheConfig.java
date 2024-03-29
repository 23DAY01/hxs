package com.lypc.hxs.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    private Map<String, RedisCacheConfiguration> TTlParam = new HashMap<>();

    //@Override
    //@Bean
    //public KeyGenerator keyGenerator() {
    //    return (o, method, objects) -> {
    //        StringBuilder sb = new StringBuilder();
    //        sb.append(o.getClass().getName()).append(".");
    //        sb.append(method.getName()).append(".");
    //        for (Object obj : objects) {
    //            sb.append(obj.toString());
    //        }
    //        return sb.toString();
    //    };
    //}

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String t = target.getClass().getName();
            String m = method.getName();
            return String.format("%s:%s(%s)", t, m, CollectionUtils.arrayToList(params));
        };
    }

    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(Objects.requireNonNull(cacheManager()));
    }

    @Override
    public CacheErrorHandler errorHandler() {
        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
        return new SimpleCacheErrorHandler();
    }


    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.
                defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .computePrefixWith(name -> name + ":")
                // 设置key采用String的序列化方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8))
                //当value为null时不进行缓存
                .disableCachingNullValues()
                //设置value序列化方式采用jackson方式序列化
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                //事务感知
                .transactionAware()
                //.withInitialCacheConfigurations(CacheNameTimeConstant.initConfigs(redisCacheConfiguration))
                //定制过期时间
                .withInitialCacheConfigurations(TTlParam)
                .build();
    }

    //定制过期时间
    public void initTTLParam() {

        //可以通过一个常量类CacheNameTimeConstant来定制 这里就先这么写了
        //.withInitialCacheConfigurations(CacheNameTimeConstant.initConfigs(redisCacheConfiguration))


        TTlParam.put("users", RedisCacheConfiguration
                .defaultCacheConfig().entryTtl(Duration.ofSeconds(60000)));
    }

}
