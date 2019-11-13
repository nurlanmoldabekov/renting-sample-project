package com.kiko.rent.service;

import com.kiko.rent.model.Application;

import java.util.List;

public interface ApplicationService {
    Application insertOrUpdate(Application Application);
    List<Application> getAll();
    Application getById(Long id);
    void deleteById(Long id);
    void approve(Long id);
    void reject(Long id);
}
