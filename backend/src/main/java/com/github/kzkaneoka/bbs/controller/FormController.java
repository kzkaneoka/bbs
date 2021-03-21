package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.enums.UserRole;
import com.github.kzkaneoka.bbs.model.Form;
import com.github.kzkaneoka.bbs.model.User;
import com.github.kzkaneoka.bbs.repository.FormRepository;
import com.github.kzkaneoka.bbs.repository.UserRepository;
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
public class FormController {

    @Autowired
    FormRepository formRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/forms")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Form>> getAllForms() {
        List<Form> forms = new ArrayList<>();
        formRepository.findAll().forEach(forms::add);
        if (forms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    @GetMapping("/forms/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Form> getFormById(@PathVariable("id") UUID id, Principal principal) {
        Optional<Form> formData = formRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !formData.get().getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(formData.get(), HttpStatus.OK);
    }

    @PostMapping("/forms")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Form> createForm(@RequestBody Form form, Principal principal) {
        if (form.getTitle() == null
                || form.getTitle().isEmpty()
                || form.getDescription() == null
                || form.getDescription().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User _user = userRepository.findByUsername(principal.getName());
        Form _form = formRepository.save(new Form(form.getTitle(), form.getDescription(), _user));
        return new ResponseEntity<>(_form, HttpStatus.CREATED);
    }

    @PutMapping("/forms/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Form> updateForm(@PathVariable("id") UUID id, @RequestBody Form form, Principal principal) {
        Optional<Form> formData = formRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !formData.get().getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (form.getTitle() != null && !form.getTitle().isEmpty()) {
            formData.get().setTitle(form.getTitle());
        }
        if (form.getDescription() != null && !form.getDescription().isEmpty()){
            formData.get().setDescription(form.getDescription());
        }
        return new ResponseEntity<>(formRepository.save(formData.get()), HttpStatus.OK);
    }

    @DeleteMapping("/forms/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteForm(@PathVariable("id") UUID id, Principal principal) {
        Optional<Form> formData = formRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !formData.get().getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        formRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/forms")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllForms() {
        formRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
