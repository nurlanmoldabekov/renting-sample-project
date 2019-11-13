package com.kiko.rent.controller;

import com.kiko.rent.model.Flat;
import com.kiko.rent.service.FlatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/flat")
public class FlatController {
    private static final Logger logger = LoggerFactory.getLogger(FlatController.class);

    @Autowired
    private FlatService service;


    @PostMapping("/")
    public ResponseEntity<?> post(@Valid @RequestBody Flat input) {

        return ResponseEntity.ok(service.insertOrUpdate(input));
    }

    @PutMapping("/")
    public ResponseEntity<?> put(@Valid @RequestBody Flat input) {

        return ResponseEntity.ok(service.insertOrUpdate(input));
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
