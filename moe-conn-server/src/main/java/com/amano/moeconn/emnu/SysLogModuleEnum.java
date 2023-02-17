package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysLogModuleEnum implements IEnum<String> {
    USER("USER", "用户模块"),
    ROLE("ROLE", "角色模块"),
    RESOURCE("RESOURCE", "资源模块");

    private String operModule;
    private String desc;

    @Override
    public String getValue() {
        return operModule;
    }
}
