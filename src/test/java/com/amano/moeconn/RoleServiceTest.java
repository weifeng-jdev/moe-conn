package com.amano.moeconn;

import com.amano.moeconn.dto.ResourceRoleDTO;
import com.amano.moeconn.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class RoleServiceTest {
    @Resource
    RoleService roleService;

    @Test
    public void testListAllResourceRole() throws JsonProcessingException {
        List<ResourceRoleDTO> resourceRoleDTOS = roleService.listAllResourceRole();
        log.info("result:", new ObjectMapper().writeValueAsString(resourceRoleDTOS));
    }
}
