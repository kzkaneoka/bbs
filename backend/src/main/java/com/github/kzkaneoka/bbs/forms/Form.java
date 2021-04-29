package com.github.kzkaneoka.bbs.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kzkaneoka.bbs.comments.Comment;
import com.github.kzkaneoka.bbs.users.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forms")
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private FormStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments;

    public Form(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.status = FormStatus.OPEN;
    }
}
