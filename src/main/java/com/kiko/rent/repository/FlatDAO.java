package com.kiko.rent.repository;

import com.kiko.rent.entity.FlatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatDAO extends CrudRepository<FlatEntity, Long> {
 
}