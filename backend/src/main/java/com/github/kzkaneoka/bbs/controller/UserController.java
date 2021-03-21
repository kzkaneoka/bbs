package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.enums.UserRole;
import com.github.kzkaneoka.bbs.exception.RestControllerExceptionHandler;
import com.github.kzkaneoka.bbs.model.User;
import com.github.kzkaneoka.bbs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id, Principal principal) {
        Optional<User> userData = userRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !userData.get().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user, Principal principal) {
        Optional<User> userData = userRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !userData.get().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") UUID id, Principal principal) {
        Optional<User> userData = userRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !userData.get().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
