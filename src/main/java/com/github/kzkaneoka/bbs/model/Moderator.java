package com.github.kzkaneoka.bbs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "moderators")
public class Moderator extends User {
    public Moderator(String username, String email, String password) {
        super(username, email, password);
    }
}
