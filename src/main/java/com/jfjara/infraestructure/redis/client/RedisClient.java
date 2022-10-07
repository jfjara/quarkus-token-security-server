package com.jfjara.infraestructure.redis.client;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Base64;
import java.util.List;
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

    public Boolean exist(final String username, final String token) {
        Optional<String> tokenRecovered = get(username);
        return tokenRecovered.filter(t -> t.equals(token)).isPresent();
    }

    public void set(String key, String value) {
        countCommands.setex(key, 600, value);
    }

}
