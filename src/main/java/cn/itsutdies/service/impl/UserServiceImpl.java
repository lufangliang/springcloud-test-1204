package cn.itsutdies.service.impl;

import cn.itsutdies.dao.UserDao;
import cn.itsutdies.pojo.User;
import cn.itsutdies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Farewell is well
 * @date 2019-12-04 11:25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean register(User user) {
        try {
            user.setLocked(1);
            userDao.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findOne(User user) {
        Optional<User> result = userDao.findOne(Example.of(user));
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public Boolean checkEmail(Integer id) {
        Optional<User> result = userDao.findById(id);
        if (result.isPresent()) {
            User user = result.get();
            user.setLocked(0);
            userDao.save(user);
            return true;
        }
        return false;
    }
}
