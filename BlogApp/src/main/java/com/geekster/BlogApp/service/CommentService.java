package com.geekster.BlogApp.service;

import com.geekster.BlogApp.model.Comment;
import com.geekster.BlogApp.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public void addComment(Comment newComment) {
        commentRepo.save(newComment);
    }



    public Comment findCommentByCommentId(Long commentId) {
        return commentRepo.findByCommentId(commentId);
    }

    public void removeCommentById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
