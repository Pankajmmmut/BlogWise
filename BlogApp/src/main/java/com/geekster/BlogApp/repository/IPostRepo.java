package com.geekster.BlogApp.repository;

import com.geekster.BlogApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post, Long> {
    Post findPostByPostId(Long postId);
}
