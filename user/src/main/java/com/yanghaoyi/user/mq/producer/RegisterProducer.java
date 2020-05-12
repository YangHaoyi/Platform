package com.yanghaoyi.user.mq.producer;

import com.yanghaoyi.user.config.RabbitConfig;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.pojo.result.VerifyCodeResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author : YangHaoYi on 2020/4/30.
 * Email  :  yang.haoyi@qq.com
 * Description :消息的生产者
 * Change : YangHaoYi on 2020/4/30.
 * Version : V 1.0
 */

@Log4j2
@Component
public class RegisterProducer implements RabbitTemplate.ConfirmCallback {
    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public RegisterProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendMsg(UserEntity userEntity) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_USER对应的队列当中去，对应的是队列USER
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_USER, RabbitConfig.ROUTINGKEY_USER, userEntity, correlationId);
//        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_USER, RabbitConfig.ROUTINGKEY_EMAIL, userEntity, correlationId);
    }

    public void sendVerifyCode(VerifyCodeResult verifyCodeResult){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_USER, RabbitConfig.ROUTINGKEY_EMAIL, verifyCodeResult, correlationId);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(" 回调id:" + correlationData);
        if (ack) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:" + cause);
        }
    }
}


/**
 * 参考网址
 * 发邮件
 * https://blog.csdn.net/weixin_44915703/article/details/104417030
 * 认证
 * https://blog.csdn.net/qq_33556185/article/details/51028952
 *
 * **/
