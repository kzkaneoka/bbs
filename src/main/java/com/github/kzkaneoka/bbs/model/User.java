package com.github.kzkaneoka.bbs.model;

import com.github.kzkaneoka.bbs.enums.UserRole;
import com.github.kzkaneoka.bbs.enums.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @OneToMany(mappedBy = "user")
    private Set<Form> forms;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = UserRole.NORMAL;
        this.status = UserStatus.ACTIVE;

    }
}
