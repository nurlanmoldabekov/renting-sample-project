package com.kiko.rent.service;

import com.kiko.rent.model.Flat;

import java.util.List;

public interface FlatService {
    Flat insertOrUpdate(Flat Flat);
    List<Flat> getAll();
    Flat getById(Long id);
    void deleteById(Long id);
}
