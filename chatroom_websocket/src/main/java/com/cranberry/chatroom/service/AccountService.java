package com.cranberry.chatroom.service;

import com.cranberry.chatroom.dao.AccountDao;
import com.cranberry.chatroom.entity.User;

public class AccountService {
    private AccountDao accountDao = new AccountDao();

    public boolean userLogin(String userName,String password){
        User user = accountDao.userLogin(userName,password);
        if (user == null) {
            return false;
        }
        return true;
    }
    public boolean userRegister(String userName ,String password ){
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        return accountDao.userRegister(user);
    }
}
