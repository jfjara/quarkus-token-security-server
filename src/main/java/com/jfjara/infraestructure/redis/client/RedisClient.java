package com.jfjara.infraestructure.redis.client;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RedisClient {

    private ReactiveKeyCommands<String> keyCommands;
    private ValueCommands<String, String> countCommands;

    public RedisClient(RedisDataSource ds, ReactiveRedisDataSource reactive) {
        countCommands = ds.value(String.class);
        keyCommands = reactive.key();
    }

    public Optional<String> get(String key) {
        String value = countCommands.get(key);
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(value);
    }

    public void set(String key, String value) {
        countCommands.setex(key, 600, value);
    }

}
