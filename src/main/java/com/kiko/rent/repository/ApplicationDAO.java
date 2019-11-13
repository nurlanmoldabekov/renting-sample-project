package com.kiko.rent.repository;

import com.kiko.rent.entity.ApplicationEntity;
import com.kiko.rent.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ApplicationDAO extends CrudRepository<ApplicationEntity, Long> {
    @Query(value = "SELECT count(*) FROM t_applications WHERE ntimeslot=?1 AND ddate=?2 AND nflatid=?3 AND vstatus=?4", nativeQuery = true)
    Integer countByTimeSlotAndDateAndFlat(Integer timeSlot, Date date, Long flatId, String status);
}