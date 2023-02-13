package com.amano.moeconn.security.handler;

import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.dto.ResourceRoleDTO;
import com.amano.moeconn.service.RoleService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 加载授权信息数据源
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    @Resource
    private RoleService roleService;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 加载资源对应的所有角色信息
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        List<ResourceRoleDTO> resourceRoleS = roleService.listAllResourceRole();
        for (ResourceRoleDTO resourceRole : resourceRoleS) {
            if (!antPathMatcher.match(resourceRole.getUrl(), requestURI)) {
               continue;
            }
            List<RoleDO> roleList = resourceRole.getRoleList();
            if (CollectionUtils.isEmpty(roleList)) {
                SecurityConfig.createList("disable");
            }
            return SecurityConfig.createList(roleList.stream().map(RoleDO::getCode).collect(Collectors.toList()).toArray(new String[]{}));
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
