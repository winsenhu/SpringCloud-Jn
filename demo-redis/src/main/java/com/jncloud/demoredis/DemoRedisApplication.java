package com.jncloud.demoredis;

import com.jncloud.demoredis.handler.RedisHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
@RestController
@EnableCaching
public class DemoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRedisApplication.class, args);
    }

    @Autowired
    RedisTemplate<String, Long> redisTemplate;

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Long> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RouterFunction<ServerResponse> routersFunction(RedisHandler redisHandler) {
        return RouterFunctions.route(RequestPredicates.PUT("/redis/put/{key}/{value}"), redisHandler::put).
                andRoute(RequestPredicates.GET("/redis/get/{key}"), redisHandler::get);
    }

    @GetMapping("/id/{typeKey}")
    public Long getIdByKey(@PathVariable("typeKey") String typeKey) {
        Long rs = redisTemplate.opsForValue().get(typeKey);
        rs = rs == null ? 0L : rs + 1;
        redisTemplate.opsForValue().set(typeKey, rs);
        return rs;
    }


}
