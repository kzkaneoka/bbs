package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.enums.UserRole;
import com.github.kzkaneoka.bbs.model.User;
import com.github.kzkaneoka.bbs.repository.UserRepository;
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
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getuserById(@PathVariable("id") UUID id) {
        Optional<User> userData = userRepository.findById(id);
        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = null;
        UserRole role = user.getRole();
        if (role == null) {
            _user = userRepository
                    .save(new User(user.getUsername(), user.getEmail(), user.getPassword()));
        } else {
            _user = userRepository
                    .save(new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole()));
        }
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        User _user = userData.get();
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            _user.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            _user.setEmail(user.getEmail());
        }
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") UUID id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
