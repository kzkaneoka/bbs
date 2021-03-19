package com.github.kzkaneoka.bbs.model;

import com.github.kzkaneoka.bbs.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin extends User {
    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
}
