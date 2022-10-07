package com.jfjara.infraestructure.redis.repository;

import com.jfjara.domain.model.UserDto;
import com.jfjara.domain.repository.TokenRecoveryRepository;
import com.jfjara.infraestructure.redis.client.RedisClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class TokenRedisRepository implements TokenRecoveryRepository {

    @Inject
    private RedisClient redisClient;

    public Optional<String> getUserToken(final String username) {
        return redisClient.get(username);
    }

    @Override
    public void saveToken(String key, String token) {
        redisClient.set(key, token);
    }

    @Override
    public boolean isValidToken(final String username, final String token) {
        return redisClient.exist(username, token);
    }
}
