package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.model.Admin;
import com.github.kzkaneoka.bbs.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        try {
            List<Admin> admins = new ArrayList<>();
            adminRepository.findAll().forEach(admins::add);
            if (admins.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getadminById(@PathVariable("id") UUID id) {
        Optional<Admin> adminData = adminRepository.findById(id);

        if (adminData.isPresent()) {
            return new ResponseEntity<>(adminData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
            Admin _admin = adminRepository
                    .save(new Admin(admin.getUsername(), admin.getEmail(), admin.getPassword()));
            return new ResponseEntity<>(_admin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") UUID id, @RequestBody Admin admin) {
        Optional<Admin> adminData = adminRepository.findById(id);

        if (adminData.isPresent()) {
            Admin _admin = adminData.get();
            _admin.setUsername(admin.getUsername());
            _admin.setEmail(admin.getEmail());
            _admin.setPassword(admin.getPassword());
            return new ResponseEntity<>(adminRepository.save(_admin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") UUID id) {
        try {
            adminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/admins")
    public ResponseEntity<HttpStatus> deleteAllAdmins() {
        try {
            adminRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
