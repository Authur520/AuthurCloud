package com.example.authur.common.configure;

import com.example.authur.common.service.RedisService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/11 11:34
 */

public class AuthurLettuceRedisConfigure {

    @Bean
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> tem = new RedisTemplate<>();
        tem.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(mapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用 String的序列化方式
        tem.setKeySerializer(stringRedisSerializer);
        tem.setHashKeySerializer(stringRedisSerializer);
        // value的序列化方式采用 jackson
        tem.setValueSerializer(jsonRedisSerializer);
        tem.setHashValueSerializer(jsonRedisSerializer);
        tem.afterPropertiesSet();
        return tem;
    }

    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public RedisService redisService(){
        return new RedisService();
    }


}
