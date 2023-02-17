package com.amano.moeconn.config.component;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.UserCacheDTO;
import com.amano.moeconn.service.UserService;
import com.amano.moeconn.util.RedisUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.amano.moeconn.constant.CacheConstant.USER_CACHE;

@Service
@Slf4j
public class UserCacheProcessor implements AbstractCacheProcessor<Long, String> {
    private LRUCache<Long, String> cache = CacheUtil.newLRUCache(10000);
    @Resource
    private UserService userService;

    public String getCacheByKey(Long key) {
        if (Objects.isNull(key)) {
            return null;
        }
        String value = cache.get(key);
        if (StrUtil.isNotBlank(value)) {
            return value;
        }
        // 本地不存在，拉redis
        Optional<UserCacheDTO> user
                = Optional.ofNullable((UserCacheDTO) RedisUtil.getValue(String.format(USER_CACHE, key)));
        if (!user.isPresent()) {
            return null;
        }
        // 本地不存在，redis存在，拉到本地
        cache.put(key, user.get().getNickName());
        return user.get().getNickName();
    }

    public void loadCache() {
        Page<UserDO> page = new Page<>(1, 1000);
        Page<UserDO> pageData = userService.page(page, new LambdaQueryChainWrapper<>(UserDO.class).getWrapper());
        List<UserDO> records = pageData.getRecords();
        if (CollUtil.isNotEmpty(records)) {
            Map<String, UserCacheDTO> data
                    = records.stream()
                    .collect(Collectors.toMap(user -> String.format(USER_CACHE, user.getId()), UserCacheDTO::ofDo));
            RedisUtil.mSetValue(data);
        }
    }
}
