package cn.itsutdies.component;

import cn.itsutdies.common.RabbitMQ;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Farewell is well
 * @date 2019-12-04 17:04
 */
@RabbitListener(
        bindings = {
                @QueueBinding(
                        exchange = @Exchange(value = RabbitMQ.EXCHANGE_NAME,type = ExchangeTypes.DIRECT),
                        key = RabbitMQ.MESSAGE_KEY,
                        value = @Queue(value = RabbitMQ.MESSAGE_QUEUE,autoDelete = "true")
                )
        }
)
@Component
public class SMSNotification {
    @RabbitHandler
    public void process(String message) {
        System.out.println("SMSInfo:"+message);
    }
}
