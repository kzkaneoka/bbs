package com.github.kzkaneoka.bbs.model;

import com.github.kzkaneoka.bbs.users.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    public Comment(String text, User user, Form form) {
        this.text = text;
        this.user = user;
        this.form = form;
    }
}
