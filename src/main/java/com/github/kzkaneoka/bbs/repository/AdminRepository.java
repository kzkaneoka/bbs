package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
