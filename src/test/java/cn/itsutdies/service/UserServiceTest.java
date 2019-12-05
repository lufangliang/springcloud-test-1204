package cn.itsutdies.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Farewell is well
 * @date 2019-12-04 21:52
 */
class UserServiceTest {
    @Test
    public void test() {
        BigDecimal b1 = new BigDecimal("1.26");
        System.out.println(b1.multiply(new BigDecimal("1000000000000000000000000000000000")));
        BigDecimal b2 = new BigDecimal(1.26);
        System.out.println(b2.multiply(new BigDecimal("1000000000000000000000000000000000")));
    }
}