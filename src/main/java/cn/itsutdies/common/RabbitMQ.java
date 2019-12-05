package cn.itsutdies.common;

/**
 * @author Farewell is well
 * @date 2019-12-04 11:09
 */
public class RabbitMQ {
    public static final String EXCHANGE_NAME = "login.direct";
    public static final String MESSAGE_KEY = "login.message.routingKey";
    public static final String EMAIL_KEY = "login.email.routingKey";
    public static final String MESSAGE_QUEUE = "login.message.queue";
    public static final String EMAIL_QUEUE = "login.email.queue";
}
