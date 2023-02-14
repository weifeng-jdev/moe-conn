package com.amano.moeconn.service.impl;

import com.amano.moeconn.config.properties.QQMailProperties;
import com.amano.moeconn.dao.UserDao;
import com.amano.moeconn.dao.UserRoleDao;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.domain.UserRoleDO;
import com.amano.moeconn.dto.RegisterUserInfoDTO;
import com.amano.moeconn.emnu.FlagEnum;
import com.amano.moeconn.emnu.RoleEnum;
import com.amano.moeconn.exception.BizException;
import com.amano.moeconn.service.UserAuthenticationService;
import com.amano.moeconn.util.RedisUtil;
import com.amano.moeconn.vo.RegisterSuccessVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.amano.moeconn.constant.CacheConstant.REGISTER_MAIL_CODE;
import static com.amano.moeconn.constant.CommonConstant.USER_DEFAULT_CREATOR_REGISTER;

@Service
@Slf4j
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Resource
    private QQMailProperties qqMailProperties;
    @Resource
    private Producer producer;
    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void sendMailCode(String mail) throws MessagingException {
        if (this.existsUserByMail(mail)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "该邮箱已注册！");
        }
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", qqMailProperties.getSmtpHost());
        properties.setProperty("mail.smtp.auth", qqMailProperties.getAuth());
        // 获取默认的 Session 对象
        Session session = Session.getDefaultInstance(properties);
        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);
        // 设置消息的发送者
        message.setFrom(new InternetAddress(qqMailProperties.getSendMail()));
        // 设置消息的接收者,直接发送
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail));
        // 设置主题
        message.setSubject("来自MoeConn的验证码");
        // 设置正文
        String code = producer.createText();
        message.setContent("欢迎注册MoeConn\n\n您的验证码为：" + code, "text/html;charset=UTF-8");
        // 准备发送，得到火箭
        Transport transport = session.getTransport("smtp");
        // 设置火箭的发射目标
        transport.connect("smtp.qq.com", qqMailProperties.getSendMail(), qqMailProperties.getSecretKey());
        // 设置验证码到redis
        RedisUtil.setValue(String.format(REGISTER_MAIL_CODE, mail), code);
        RedisUtil.expire(String.format(REGISTER_MAIL_CODE, mail), 15, TimeUnit.MINUTES);
        // 发送
        transport.sendMessage(message, message.getAllRecipients());
    }

    private boolean existsUserByMail(String mail) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getEmail, mail)
                .eq(UserDO::getDelFlag, FlagEnum.CLOSE.getFlag());
        return userDao.selectCount(wrapper) > 0;
    }

    @Override
    @Transactional
    public RegisterSuccessVo register(RegisterUserInfoDTO registerUserInfo) {
        if (this.existsUserByMail(registerUserInfo.getMail())) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "该邮箱已注册！");
        }
        if (!checkMailCode(registerUserInfo)) {
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "验证码不正确，请重试！");
        }
        // 生成随机用户名，配置用户基本角色，保存信息信息
        UserDO userDO = new UserDO();
        userDO.setUsername(registerUserInfo.getMail())
                .setEmail(registerUserInfo.getMail())
                .setPassword(bCryptPasswordEncoder.encode(registerUserInfo.getPassword()))
                .setNickName(registerUserInfo.getMail())
                .setCreateBy(USER_DEFAULT_CREATOR_REGISTER);
        userDao.insert(userDO);
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(userDO.getId())
                .setRoleId(RoleEnum.ROLE_USER.getRoleId());
        userRoleDao.insert(userRoleDO);
        RegisterSuccessVo registerSuccessVo = new RegisterSuccessVo();
        registerSuccessVo.setUsername(userDO.getNickName());
        return registerSuccessVo;
    }

    private boolean checkMailCode(RegisterUserInfoDTO registerUserInfo) {
        Optional<Object> code
                = Optional.ofNullable(RedisUtil.getValue(String.format(REGISTER_MAIL_CODE, registerUserInfo.getMail())));
        return code.filter(o -> Objects.equals(o.toString(), registerUserInfo.getVerifyCode())).isPresent();
    }
}
