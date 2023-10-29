package com.geekster.BlogApp.controller;

import com.geekster.BlogApp.model.Post;
import com.geekster.BlogApp.model.User;
import com.geekster.BlogApp.service.CommentService;
import com.geekster.BlogApp.service.FollowService;
import com.geekster.BlogApp.service.PostService;
import com.geekster.BlogApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    @PostMapping("user/signUp")
    public String signUpUser(@Valid @RequestBody User user){
        return userService.signUpUser(user);
    }

    @PostMapping("user/SignIn/{userEmail}/{password}")
    public String signInUser(@PathVariable String userEmail, @PathVariable String password){
        return userService.signInUser(userEmail,password);
    }

    @DeleteMapping("user/SignOut")
    public String signOutUser(@RequestParam String email, @RequestParam String tokenValue){
        return userService.signOutUser(email,tokenValue);
    }

    @PostMapping("post")
    public String createPost(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Post post)
    {
        return userService.createPost(email,tokenValue,post);
    }

    @GetMapping("posts")
    public List<Post> getPosts(@RequestParam String email, @RequestParam String tokenValue){
        return userService.getPosts(email,tokenValue);
    }

    @DeleteMapping("post/{postId}")
    public String deletePost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Long postId)
    {
        return userService.deletePost(email,tokenValue,postId);
    }


    //comment apis


    @PostMapping("comment/post/{postId}")
    public String addComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Long postId,@RequestBody String commentBody )
    {
        return userService.addComment(email,tokenValue,commentBody,postId);
    }


    @DeleteMapping("post/comment/{commentId}")
    public String removeComment(@RequestParam String email, @RequestParam String tokenValue,
                                @PathVariable Long commentId)
    {
        return userService.removeComment(email,tokenValue,commentId);
    }



    @PostMapping("follow/user/{targetUserId}")
    public String followTarget(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Long targetUserId)
    {
        return userService.followTarget(email,tokenValue,targetUserId);
    }
}
