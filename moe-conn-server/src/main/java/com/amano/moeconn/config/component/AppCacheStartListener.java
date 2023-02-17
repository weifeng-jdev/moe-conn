package com.amano.moeconn.config.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j
public class AppCacheStartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private Map<String, AbstractCacheProcessor> cacheProcessors;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (Map.Entry<String, AbstractCacheProcessor> processor : cacheProcessors.entrySet()) {
            try {
                processor.getValue().loadCache();
            } catch (Exception e) {
                log.error("{}加载缓存失败", processor.getClass().getName(), e);
            }
        }
    }
}
