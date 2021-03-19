package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.model.Moderator;
import com.github.kzkaneoka.bbs.repository.ModeratorRepository;
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
public class ModeratorController {

    @Autowired
    ModeratorRepository moderatorRepository;

    @GetMapping("/moderators")
    public ResponseEntity<List<Moderator>> getAllModerators() {
        try {
            List<Moderator> moderators = new ArrayList<>();
            moderatorRepository.findAll().forEach(moderators::add);
            if (moderators.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(moderators, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/moderators/{id}")
    public ResponseEntity<Moderator> getmoderatorById(@PathVariable("id") UUID id) {
        Optional<Moderator> moderatorData = moderatorRepository.findById(id);

        if (moderatorData.isPresent()) {
            return new ResponseEntity<>(moderatorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/moderators")
    public ResponseEntity<Moderator> createModerator(@RequestBody Moderator moderator) {
        try {
            Moderator _moderator = moderatorRepository
                    .save(new Moderator(moderator.getUsername(), moderator.getEmail(), moderator.getPassword()));
            return new ResponseEntity<>(_moderator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/moderators/{id}")
    public ResponseEntity<Moderator> updateModerator(@PathVariable("id") UUID id, @RequestBody Moderator moderator) {
        Optional<Moderator> moderatorData = moderatorRepository.findById(id);

        if (moderatorData.isPresent()) {
            Moderator _moderator = moderatorData.get();
            _moderator.setUsername(moderator.getUsername());
            _moderator.setEmail(moderator.getEmail());
            _moderator.setPassword(moderator.getPassword());
            return new ResponseEntity<>(moderatorRepository.save(_moderator), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/moderators/{id}")
    public ResponseEntity<HttpStatus> deleteModerator(@PathVariable("id") UUID id) {
        try {
            moderatorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/moderators")
    public ResponseEntity<HttpStatus> deleteAllModerators() {
        try {
            moderatorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}