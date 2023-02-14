package com.amano.moeconn.service.impl;

import com.amano.moeconn.dao.UserDao;
import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.service.RoleService;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryChainWrapper<UserDO> wrapper = new QueryChainWrapper<>(UserDO.class);
        wrapper.eq("username", username);
        UserDO user = userDao.selectOne(wrapper.getWrapper());
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 加载角色信息
        List<RoleDO> roleS = roleService.listRoleByUserId(user.getId());
        return this.BuildUserDetail(user, roleS);
    }

    private UserDetailsDTO BuildUserDetail(UserDO user, List<RoleDO> roleS) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        return userDetailsDTO.setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setAccountNonExpired(user.getAccountNonExpired().getAccountNonExpired())
                .setAccountNonLocked(user.getAccountNonLocked().getAccountNonLocked())
                .setCredentialsNonExpired(user.getCredentialsNonExpired().getCredentialsNonExpired())
                .setEnabled(user.getEnabled().getEnabled())
                .setRole(roleS);
    }
}
