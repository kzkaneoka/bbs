package com.github.kzkaneoka.bbs.model;

import com.github.kzkaneoka.bbs.enums.FormStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forms")
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private FormStatus status;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Form(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.status = FormStatus.OPEN;
        this.user = user;
    }
}
