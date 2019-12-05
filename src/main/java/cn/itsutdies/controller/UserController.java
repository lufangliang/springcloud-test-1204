package cn.itsutdies.controller;

import cn.itsutdies.common.RabbitMQ;
import cn.itsutdies.pojo.User;
import cn.itsutdies.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Farewell is well
 * @date 2019-12-04 11:24
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RabbitOperations rabbitOperations;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public Boolean userLogin(User user) {
        System.out.println(request.getServletContext());
        User u = userService.findOne(user);
        if (u != null) {
            rabbitOperations.convertAndSend(
                    RabbitMQ.EXCHANGE_NAME,
                    RabbitMQ.MESSAGE_KEY,
                    new StringBuilder()
                            .append(u.getTelephone())
                            .append("-")
                            .append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))
                            .append("-")
                            .append(request.getRemoteHost())
                            .append(":")
                            .append(request.getRemotePort())
                            .toString()
            );
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/register")
    public Boolean userRegister(User user) {
        if (userService.register(user)) {
            User u = userService.findOne(user);
            rabbitOperations.convertAndSend(RabbitMQ.EXCHANGE_NAME,
                    RabbitMQ.EMAIL_KEY,
                    new StringBuilder()
                            .append(user.getEmail())
                            .append("-")
                            .append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))
                            .append("-")
                            .append("http://")
                            .append(request.getLocalAddr())
                            .append(":")
                            .append(request.getLocalPort())
                            .append("/checkEmail")
                            .append("?id=")
                            .append(u.getId())
                            .toString()
            );
            return true;
        } else {
            return false;
        }
    }
    @RequestMapping("/checkEmail")
    public Boolean checkEmail(Integer id) {
        return userService.checkEmail(id);
    }
}
