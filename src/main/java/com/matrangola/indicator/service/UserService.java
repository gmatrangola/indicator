package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.User;

public interface UserService {
    void addUser(User user);
    void updateUser(User user) throws Exception;
    User getUser(Long id) throws Exception;

}
