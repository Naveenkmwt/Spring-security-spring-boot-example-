package com.scube.auth.service;

public interface AutoLoginService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
