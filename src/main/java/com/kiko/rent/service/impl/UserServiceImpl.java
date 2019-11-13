package com.kiko.rent.service.impl;

import com.kiko.rent.entity.UserEntity;
import com.kiko.rent.mapper.UserMapper;
import com.kiko.rent.model.User;
import com.kiko.rent.repository.UserDAO;
import com.kiko.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;
    @Autowired
    private UserMapper mapper;


    @Override
    public User insertOrUpdate(User user) {
        final UserEntity inputEntity = mapper.toEntity(user);

        return mapper.toModel(dao.save(inputEntity));
    }

    @Override
    public List<User> getAll() {
        List<User> models = new ArrayList<>();
        dao.findAll().forEach(s-> models.add(mapper.toModel(s)));
        return models;
    }

    @Override
    public User getById(Long id) {
        return mapper.toModel(dao.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
