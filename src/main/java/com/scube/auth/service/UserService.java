package com.scube.auth.service;

import java.util.List;

import com.scube.auth.model.DbUser;

public interface UserService {
    void save(DbUser user);

    DbUser findByUsername(String username);
    
    public void resetPassword(String username);;
    
   public List<DbUser> getAllUser();
}
