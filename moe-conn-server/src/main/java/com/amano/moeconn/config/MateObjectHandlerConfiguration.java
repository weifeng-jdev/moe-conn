package com.amano.moeconn.config;

import com.amano.moeconn.dto.UserDetailsDTO;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MateObjectHandlerConfiguration implements MetaObjectHandler {
    /**
     * 配置mybatis字段自动填充规则，插入时填充创建时间
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createBy", () -> {
            UserDetailsDTO loginUser = (UserDetailsDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            return loginUser.getId();
        }, Long.class);
    }

    /**
     * 配置mybatis字段自动填充规则，更新时填充修改时间, 修改人
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateBy", () -> {
            UserDetailsDTO loginUser = (UserDetailsDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            return loginUser.getId();
        }, Long.class);
    }
}