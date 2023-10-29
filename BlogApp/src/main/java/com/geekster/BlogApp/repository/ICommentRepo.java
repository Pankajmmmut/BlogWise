package com.geekster.BlogApp.repository;

import com.geekster.BlogApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Long> {


    Comment findByCommentId(Long commentId);
}
