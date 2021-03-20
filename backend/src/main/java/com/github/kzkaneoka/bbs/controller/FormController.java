package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.model.Form;
import com.github.kzkaneoka.bbs.repository.FormRepository;
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
public class FormController {

    @Autowired
    FormRepository formRepository;

    @GetMapping("/forms")
    public ResponseEntity<List<Form>> getAllForms() {
        try {
            List<Form> forms = new ArrayList<>();
            formRepository.findAll().forEach(forms::add);
            if (forms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(forms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/forms/{id}")
    public ResponseEntity<Form> getformById(@PathVariable("id") UUID id) {
        Optional<Form> formData = formRepository.findById(id);

        if (formData.isPresent()) {
            return new ResponseEntity<>(formData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/forms")
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        try {
            Form _form = formRepository
                    .save(new Form(form.getTitle(), form.getDescription(), form.getUser()));
            return new ResponseEntity<>(_form, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/forms/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable("id") UUID id, @RequestBody Form form) {
        Optional<Form> formData = formRepository.findById(id);

        if (formData.isPresent()) {
            Form _form = formData.get();
            _form.setTitle(form.getTitle());
            _form.setDescription(form.getDescription());
            _form.setUser(form.getUser());
            return new ResponseEntity<>(formRepository.save(_form), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/forms/{id}")
    public ResponseEntity<HttpStatus> deleteForm(@PathVariable("id") UUID id) {
        try {
            formRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/forms")
    public ResponseEntity<HttpStatus> deleteAllForms() {
        try {
            formRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
