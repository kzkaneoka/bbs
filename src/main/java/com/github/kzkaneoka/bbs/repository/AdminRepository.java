package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
