package com.github.kzkaneoka.bbs.controller;

import com.github.kzkaneoka.bbs.users.UserRole;
import com.github.kzkaneoka.bbs.model.Comment;
import com.github.kzkaneoka.bbs.forms.Form;
import com.github.kzkaneoka.bbs.users.User;
import com.github.kzkaneoka.bbs.repository.CommentRepository;
import com.github.kzkaneoka.bbs.forms.FormRepository;
import com.github.kzkaneoka.bbs.users.UserRepository;
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
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FormRepository formRepository;

    @GetMapping("/comments")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") UUID id) {
        Optional<Comment> commentData = commentRepository.findById(id);
        return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
    }

    @GetMapping("/forms/{formId}/comments")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Comment>> getCommentByFormId(@PathVariable("formId") UUID formId) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAllByFormId(formId).forEach(comments::add);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/forms/{formId}/comments")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> createComment(@PathVariable("formId") UUID formId,
                                                 @RequestBody Comment comment, Principal principal) {
        Optional<Form> formData = formRepository.findById(formId);
        User _user = userRepository.findByUsername(principal.getName());
        Comment _comment = commentRepository.save(new Comment(comment.getText(), _user, formData.get()));
        return new ResponseEntity<>(_comment, HttpStatus.CREATED);
    }

    @PutMapping("/forms/{formId}/comments/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComment(@PathVariable("formId") UUID formId,
                                                 @PathVariable("id") UUID id,
                                                 @RequestBody Comment comment, Principal principal) {
        Optional<Comment> commentData = commentRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !commentData.get().getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!commentData.get().getForm().getId().equals(formId)
                || commentData.get().getText() == null
                || commentData.get().getText().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Comment _comment = commentData.get();
        _comment.setText(comment.getText());
        return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
    }

    @DeleteMapping("/forms/{formId}/comments/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("formId") UUID formId,
                                                    @PathVariable("id") UUID id,
                                                    Principal principal) {
        Optional<Comment> commentData = commentRepository.findById(id);
        User loggedInUser = userRepository.findByUsername(principal.getName());
        if (!loggedInUser.getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.ROLE_ADMIN))
                && !commentData.get().getUser().getUsername().equals(principal.getName())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!commentData.get().getForm().getId().equals(formId)
                || commentData.get().getText() == null
                || commentData.get().getText().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        commentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/comments")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllComments() {
        commentRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
