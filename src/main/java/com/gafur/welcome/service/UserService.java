package com.gafur.welcome.service;

import com.gafur.welcome.model.User;

/**
 * User service
 *
 * @author igafurov
 * @since 01.11.2016
 */
public interface UserService {

    /**
     * Save user
     *
     * @param user user entity
     */
    void save(User user);

    /**
     * Find user by user name
     *
     * @param username      user form
     * @return user entity
     */
    User findByUsername(String username);
}
