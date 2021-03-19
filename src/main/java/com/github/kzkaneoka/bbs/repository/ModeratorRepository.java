package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModeratorRepository extends JpaRepository<Moderator, UUID> {
}
