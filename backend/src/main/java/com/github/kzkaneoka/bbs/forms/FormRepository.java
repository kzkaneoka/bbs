package com.github.kzkaneoka.bbs.forms;

import com.github.kzkaneoka.bbs.forms.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {
}
