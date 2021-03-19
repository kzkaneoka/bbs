package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
}
