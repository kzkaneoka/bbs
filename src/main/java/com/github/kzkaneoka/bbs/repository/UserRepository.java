package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
