package com.github.kzkaneoka.bbs.comments;

import com.github.kzkaneoka.bbs.comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByFormId(UUID formId);
}
