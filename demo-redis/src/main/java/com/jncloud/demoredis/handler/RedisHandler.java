package com.jncloud.demoredis.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RedisHandler {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public Mono<ServerResponse> put(ServerRequest request) {
        String key = request.pathVariable("key");
        String value = request.pathVariable("value");
        stringRedisTemplate.opsForValue().set(key, value);
        return ServerResponse.ok().body(Mono.just("{" + key + "=" + value + "}"), String.class);
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        String key = request.pathVariable("key");
        String value = stringRedisTemplate.opsForValue().get(key);
        return ServerResponse.ok().body(Mono.just("{" + key + "=" + value + "}"), String.class);
    }

}
