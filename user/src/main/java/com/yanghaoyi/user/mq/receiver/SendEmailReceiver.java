package com.yanghaoyi.user.mq.receiver;

import com.yanghaoyi.user.config.RabbitConfig;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.util.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : YangHaoYi on 2020/4/30.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/30.
 * Version : V 1.0
 */
@Log4j2
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_EMAIL)
public class SendEmailReceiver {

    @Resource
    private RedisUtil redisUtil;

//    @Autowired
//    private JavaMailSender mailSender;

    @RabbitHandler
    public void process(UserEntity userEntity) {
        log.warn("发送邮件 ");
//        redisUtil.set("user"+userEntity.getId(), userEntity, 60);
//        log.warn("插入redis库结束 ");

//        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
//        message.setFrom("1061097519@qq.com");//发件人
//        message.setTo("qweyhy@qq.com");//收件人
//        message.setSubject("OA注册");//主题
//        message.setText("注册成功!!");//正文
//        mailSender.send(message);

//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.qq.com");
//        mailSender.setPort(25);
//        mailSender.setUsername("1061057519@qq.com");
//        mailSender.setPassword("");
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("1061057519@qq.com");
//        message.setTo("qweyhy@qq.com");
//        message.setText(userEntity.getUserName() + "  注册成功");
//        mailSender.send(message);
    }
}
