package cn.itsutdies.service;

import cn.itsutdies.pojo.User;

/**
 * @author Farewell is well
 * @date 2019-12-04 11:24
 */
public interface UserService {
    Boolean register(User user);

    User findOne(User user);

    Boolean checkEmail(Integer id);
}
