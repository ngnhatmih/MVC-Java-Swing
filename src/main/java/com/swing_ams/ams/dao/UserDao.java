package com.swing_ams.ams.dao;

import com.swing_ams.ams.model.User;

public class UserDao {

    public boolean checkUser(User user) {
        if (user != null) {
            return "admin".equals(user.getUserName())
                    && "123".equals(user.getPassword());
        }
        return false;
    }
}
