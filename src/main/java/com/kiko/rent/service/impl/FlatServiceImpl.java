package com.kiko.rent.service.impl;

import com.kiko.rent.entity.FlatEntity;
import com.kiko.rent.entity.UserEntity;
import com.kiko.rent.exception.ConflictException;
import com.kiko.rent.mapper.FlatMapper;
import com.kiko.rent.model.Flat;
import com.kiko.rent.repository.FlatDAO;
import com.kiko.rent.repository.UserDAO;
import com.kiko.rent.service.FlatService;
import com.kiko.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlatServiceImpl implements FlatService {

    @Autowired
    private FlatDAO dao;
    @Autowired
    private FlatMapper mapper;
    @Autowired
    private UserDAO userDAO;

    @Override
    public Flat insertOrUpdate(Flat flat) {
        UserEntity userEntity = userDAO.findById(flat.getUserId())
                .orElseThrow(() ->  new ConflictException("No user was found"));

        FlatEntity inputEntity = mapper.toEntity(flat);
        inputEntity.setUser(userEntity);
        return mapper.toModel(dao.save(inputEntity));
    }

    @Override
    public List<Flat> getAll() {
        List<Flat> models = new ArrayList<>();
        dao.findAll().forEach(s-> models.add(mapper.toModel(s)));
        return models;
    }

    @Override
    public Flat getById(Long id) {
        return mapper.toModel(dao.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
