package com.gafur.welcome.service;

public interface AuthenticationService {

    String findLoggedInUsername();
    void autologin(String username, String password);
}
