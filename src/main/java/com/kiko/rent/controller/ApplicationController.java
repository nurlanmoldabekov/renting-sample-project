package com.kiko.rent.controller;

import com.kiko.rent.model.Application;
import com.kiko.rent.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/application")
public class ApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService service;


    @PostMapping("/")
    public ResponseEntity<?> post(@Valid @RequestBody Application input) {

        return ResponseEntity.ok(service.insertOrUpdate(input));
    }

    @PutMapping("/")
    public ResponseEntity<?> put(@Valid @RequestBody Application input) {

        return ResponseEntity.ok(service.insertOrUpdate(input));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {

        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable("id") Long id) {
        service.reject(id);
        return ResponseEntity.ok("Done");
    }
    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable("id") Long id) {
        service.approve(id);
        return ResponseEntity.ok("Done");
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
