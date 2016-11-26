package com.gafur.welcome.service;

import com.gafur.welcome.model.User;

public interface UserService {

    void save(User user);
    User findByUsername(String username);
}
