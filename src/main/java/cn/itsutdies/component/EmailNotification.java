package cn.itsutdies.component;

import cn.itsutdies.common.RabbitMQ;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Farewell is well
 * @date 2019-12-04 17:05
 */
@RabbitListener(
        bindings = {
                @QueueBinding(
                        exchange = @Exchange(value = RabbitMQ.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
                        key = RabbitMQ.EMAIL_KEY,
                        value = @Queue(value = RabbitMQ.EMAIL_QUEUE,autoDelete = "true")
                )
        }
)
@Component
public class EmailNotification {
    @Autowired
    private JavaMailSender javaMailSender;
    @RabbitHandler
    public void process(String message) {
        String[] strings = message.split("-");
        if (strings.length != 3) {
            return;
        } else {
            StringBuilder receiver = new StringBuilder(strings[0]);
            StringBuilder time = new StringBuilder(strings[1]);
            StringBuilder url = new StringBuilder(strings[2]);
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("327654444@qq.com");
            email.setTo(receiver.toString());
            email.setSubject("账户邮箱激活");
            email.setText("您于"+time+"注册了账户，请点击以下链接激活您的账户:"+url);
            try {
                javaMailSender.send(email);
                System.out.println("激活邮件已送达"+receiver);
            } catch (MailException e) {
                System.out.println("激活邮件发送失败！");
                System.out.println("123");
            }
        }


    }
}
