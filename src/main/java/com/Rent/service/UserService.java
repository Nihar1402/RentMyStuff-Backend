package com.Rent.service;

import com.Rent.models.User;

public interface UserService {

    User findByEmail(String email);
    void save(User user);
}
