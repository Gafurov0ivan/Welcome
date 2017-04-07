package com.gafur.welcome.service;

/**
 * Authentication service
 *
 * @author igafurov
 * @since 01.11.2016
 */
public interface AuthenticationService {

    /**
     * Find logged user
     *
     * @return user name
     */
    String findLoggedInUsername();

    /**
     * Auto login
     *
     * @param username user name
     * @param password password
     */
    void autologin(String username, String password);
}
