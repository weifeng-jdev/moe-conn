package com.amano.moeconn.config.component;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsernameSerializerConverter extends StdConverter<Long, String> {
    @Resource
    private UserCacheProcessor userCacheProcessor;

    @Override
    public String convert(Long userId) {
        return userCacheProcessor.getCacheByKey(userId);
    }
}
