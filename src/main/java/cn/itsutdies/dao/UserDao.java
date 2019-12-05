package cn.itsutdies.dao;

import cn.itsutdies.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Farewell is well
 * @date 2019-12-04 11:24
 */
public interface UserDao extends JpaRepository<User,Integer> {
}
