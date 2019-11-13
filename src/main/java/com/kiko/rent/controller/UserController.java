package com.kiko.rent.controller;

import com.kiko.rent.model.User;
import com.kiko.rent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;


    @PostMapping("/")
    public ResponseEntity<?> post(@Valid @RequestBody User user) {

        return ResponseEntity.ok(service.insertOrUpdate(user));
    }

    @PutMapping("/")
    public ResponseEntity<?> put(@Valid @RequestBody User user) {

        return ResponseEntity.ok(service.insertOrUpdate(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Done");
    }


}
