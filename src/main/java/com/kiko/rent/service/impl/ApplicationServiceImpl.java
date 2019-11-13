package com.kiko.rent.service.impl;

import com.kiko.rent.entity.ApplicationEntity;
import com.kiko.rent.entity.FlatEntity;
import com.kiko.rent.entity.UserEntity;
import com.kiko.rent.exception.ConflictException;
import com.kiko.rent.mapper.ApplicationMapper;
import com.kiko.rent.model.Application;
import com.kiko.rent.model.enums.ApplicationStatus;
import com.kiko.rent.model.enums.UserRole;
import com.kiko.rent.repository.ApplicationDAO;
import com.kiko.rent.repository.FlatDAO;
import com.kiko.rent.repository.UserDAO;
import com.kiko.rent.service.ApplicationService;
import com.kiko.rent.util.NotificationUtil;
import com.kiko.rent.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static final String NO_APP_FOUND_MESSAGE = "No application was found";
    @Autowired
    private ApplicationDAO dao;
    @Autowired
    private ApplicationMapper mapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FlatDAO flatDAO;

    @Override
    public Application insertOrUpdate(Application app) {


        UserEntity newUser = userDAO.findById(app.getNewUserId())
                .orElseThrow(() -> new ConflictException("No new user was found"));
        UserEntity oldUser = userDAO.findById(app.getOldUserId())
                .orElseThrow(() -> new ConflictException("No old user was found"));
        FlatEntity flat = flatDAO.findById(app.getFlatId())
                .orElseThrow(() -> new ConflictException("No flat was found"));
        validate(app);
        validateUsers(oldUser, newUser);
        validateFlat(oldUser, flat);
        ApplicationEntity inputEntity = mapper.toEntity(app);

        inputEntity.setNewUser(newUser);
        inputEntity.setOldUser(oldUser);
        inputEntity.setFlat(flat);
        Application res = mapper.toModel(dao.save(inputEntity));

        NotificationUtil.notifyUser(oldUser.getEmail(), String.format("Application for you flat %s created", flat.getAddress()));

        return res;
    }

    @Override
    public List<Application> getAll() {
        List<Application> models = new ArrayList<>();
        dao.findAll().forEach(s -> {
            Application rs = mapper.toModel(s);
            rs.setTimeInterpretation(TimeUtils.timeSlotToString(rs.getTimeSlot(), 10L, rs.getDate()));
            models.add(rs);

        });
        return models;
    }

    @Override
    public Application getById(Long id) {
        Application res = mapper.toModel(dao.findById(id).orElseThrow(()-> new ConflictException(NO_APP_FOUND_MESSAGE)));
        res.setTimeInterpretation(TimeUtils.timeSlotToString(res.getTimeSlot(), 10L, res.getDate()));
        return res;
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void approve(Long id) {
        ApplicationEntity app = dao.findById(id).orElseThrow(() -> new ConflictException(NO_APP_FOUND_MESSAGE));
        app.setStatus(ApplicationStatus.APPROVED);
        dao.save(app);
        NotificationUtil.notifyUser(app.getNewUser().getEmail(), String.format("Your application for flat %s was approved", app.getFlat().getAddress()));
    }

    @Override
    public void reject(Long id) {
        ApplicationEntity app = dao.findById(id).orElseThrow(() -> new ConflictException(NO_APP_FOUND_MESSAGE));
        app.setStatus(ApplicationStatus.REJECTED);
        dao.save(app);
        NotificationUtil.notifyUser(app.getNewUser().getEmail(), String.format("Your application for flat %s was rejected", app.getFlat().getAddress()));
    }


    private void validate(Application app) {
        if (app.getNewUserId().equals(app.getOldUserId())) {
            throw new ConflictException("Current User shouldn't rent this flat");
        }
        if (dao.countByTimeSlotAndDateAndFlat(app.getTimeSlot(), app.getDate(), app.getFlatId(), app.getStatus().name()) > 0) {
            throw new ConflictException("Current time slot is taken");
        }
    }

    private void validateUsers(UserEntity oldUser, UserEntity newUser) {
        if (!oldUser.getRole().equals(UserRole.CURRENT_TENANT)) {
            throw new ConflictException("Current user role is incorrect");
        }
        if (!newUser.getRole().equals(UserRole.NEW_TENANT)) {
            throw new ConflictException("New user role is incorrect");
        }
    }
    private void validateFlat(UserEntity oldUser, FlatEntity flatEntity){
        if (!flatEntity.getUser().getId().equals(oldUser.getId())){
            throw new ConflictException("Current user is not the owner of the flat");
        }
    }
}
