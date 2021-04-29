package com.github.kzkaneoka.bbs.users;

import com.github.kzkaneoka.bbs.users.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole name;

    public Role(UserRole name) {
        this.name = name;
    }
}
