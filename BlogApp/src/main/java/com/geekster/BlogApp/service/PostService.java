package com.geekster.BlogApp.service;

import com.geekster.BlogApp.model.Post;
import com.geekster.BlogApp.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public void createPost(Post post) {
        post.setPostCreationTime(LocalDateTime.now());

        postRepo.save(post);
    }

    public Post getPostByPostId(Long postId) {
        return postRepo.findPostByPostId(postId);
    }

    public void removeById(Long postId) {
        postRepo.deleteById(postId);
    }

}
