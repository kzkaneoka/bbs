package com.github.kzkaneoka.bbs.repository;

import com.github.kzkaneoka.bbs.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
