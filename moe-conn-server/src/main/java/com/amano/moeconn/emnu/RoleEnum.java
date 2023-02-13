package com.amano.moeconn.emnu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ROLE_ADMIN(1L, "管理员", "ROLE_ADMIN"),
    ROLE_USER(2L, "普通用户", "ROLE_USER");

    private final Long roleId;
    private final String roleName;
    private final String roleCode;
}
