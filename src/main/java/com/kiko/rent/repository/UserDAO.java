package com.kiko.rent.repository;

import com.kiko.rent.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Long> {
 
}