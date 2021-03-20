package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FormRepository extends JpaRepository<Form, UUID> {
}
