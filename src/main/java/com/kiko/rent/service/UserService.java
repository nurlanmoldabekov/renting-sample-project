package com.kiko.rent.service;

import com.kiko.rent.model.User;

import java.util.List;

public interface UserService {
    User insertOrUpdate(User user);
    List<User> getAll();
    User getById(Long id);
    void deleteById(Long id);
}
