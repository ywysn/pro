package com.cranberry.chatroom.dao;

import com.cranberry.chatroom.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class AccountDaoTest {
    private AccountDao accountDao = new AccountDao();

    @Test
    public void userLogin() {
        User user = new User();
        Assert.assertNotNull(user);
    }

    @Test
    public void userRegister() {
        User user = new User();
        user.setUsername("thua");
        user.setPassword("123");
        boolean isSuccess = accountDao.userRegister(user);
        Assert.assertEquals(true,isSuccess);
    }
}