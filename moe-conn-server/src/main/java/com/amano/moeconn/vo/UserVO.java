package com.amano.moeconn.vo;

import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.emnu.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@ApiModel("用户视图模型")
@Accessors(chain = true)
public class UserVO {
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private GenderEnum gender;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("简介")
    private String introduce;

    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long createBy;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
    private Long UpdateBy;

    @ApiModelProperty("用户是否可用")
    private String enabled;

    /**
     * 将数据库do实体类转换成vo实体类
     *
     * @param userDO do实体类
     *
     * @return vo实体类
     */
    public static UserVO ofDo(UserDO userDO) {
        return new UserVO()
                .setId(userDO.getId())
                .setNickName(userDO.getNickName())
                .setEmail(userDO.getEmail())
                .setGender(userDO.getGender())
                .setMobile(userDO.getMobile())
                .setIntroduce(userDO.getIntroduce())
                .setSign(userDO.getSign())
                .setCreateTime(userDO.getCreateTime())
                .setCreateBy(userDO.getCreateBy())
                .setUpdateTime(userDO.getUpdateTime())
                .setUpdateBy(userDO.getUpdateBy())
                .setEnabled(userDO.getEnabled().getStats());
    }
}
