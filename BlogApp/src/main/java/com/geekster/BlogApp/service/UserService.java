package com.geekster.BlogApp.service;

import com.geekster.BlogApp.model.AuthenticationToken;
import com.geekster.BlogApp.model.Post;
import com.geekster.BlogApp.model.User;
import com.geekster.BlogApp.model.Comment;
import com.geekster.BlogApp.repository.IAuthRepo;
import com.geekster.BlogApp.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAuthRepo authRepo;

    @Autowired
    AuthService authService;
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    FollowService followService;

    public String signUpUser(User user) {

        String newEmail = user.getUserEmail();
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if(existingUser == null){
            userRepo.save(user);
            return "successfully signed up.";

        }
        return "email already in use.";


    }

    public String signInUser( String userEmail,String password) {

       User existingUser = userRepo.findFirstByUserEmail(userEmail);
       if(existingUser.getPassword().equals(password)){
           AuthenticationToken token = new AuthenticationToken(existingUser);
           token.setCreationDateTime(LocalDateTime.now());
           authRepo.save(token);
           return "successfully signed in. Please copy token : "+token.getTokenValue();
       }
       else {
           return "email or password incorrect.";
       }
    }


    public String signOutUser(String email, String tokenValue) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token == null){
            return "Incorrect token!";
        }
        authRepo.delete(token);
        return "Signed out.";
    }

    public String createPost(String email, String tokenValue, Post post) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token == null){
            return "Invalid access";
        }
        User author = userRepo.findFirstByUserEmail(email);
        post.setAuthor(author);
            postService.createPost(post);
            return "posted!!";

    }

    public String deletePost(String email, String tokenValue, Long postId) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token == null){
            Post post =  postService.getPostByPostId(postId);
            String  postOwnerEmail =  post.getAuthor().getUserEmail();

            if(email.equals(postOwnerEmail))
            {
                postService.removeById(postId);
                return "post removed!!";

            }
            else {
                return "Un authorized access!!";
            }
        }
        return "error";
    }

    public String addComment(String email, String tokenValue, String commentBody, Long postId) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token == null){

            Post postToBeCommented = postService.getPostByPostId(postId);

            User commentor = userRepo.findFirstByUserEmail(email);

            Comment newComment = new Comment(null,commentBody,
                    LocalDateTime.now(), commentor, postToBeCommented);

            commentService.addComment(newComment);

            return commentor.getHandle() + " commented on " + postId;


        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String removeComment(String email, String tokenValue, Long commentId) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token == null){
            Comment comment = commentService.findCommentByCommentId(commentId);

            Post postOfComment = comment.getPost();


            if(authorizeCommentRemover(email,postOfComment,comment))
            {
                commentService.removeCommentById(commentId);
                return "comment deleted";
            }
            else {
                return "Not authorized!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }
    private boolean authorizeCommentRemover(String email,Post postOfComment, Comment comment) {

        User potentialRemover = userRepo.findFirstByUserEmail(email);


        return potentialRemover.equals(postOfComment.getAuthor()) || potentialRemover.equals(comment.getUser());

    }

    public String followTarget(String email, String tokenValue, Long targetUserId) {
        if(authService.authenticate(email,tokenValue)) {

            User follower = userRepo.findFirstByUserEmail(email);
            User target = userRepo.findById(targetUserId).orElseThrow();

            if(authorizeToFollow(follower,target))
            {

                followService.startFollowing(follower,target);
                return follower.getHandle() + " started following " + target.getHandle();
            }
            else {
                return "Already follows, cannot re-follow";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }
    private boolean authorizeToFollow(User follower, User target) {

        //check if already follows or not

        boolean followingExist =  followService.findByTargetAndFollower(follower,target);

        return !followingExist && !follower.equals(target);

    }

    public List<Post> getPosts(String email, String tokenValue) {
        if(authService.authenticate(email,tokenValue))
        {
            return postService.postRepo.findAll();
        }
        return postService.postRepo.findAll();
    }
}
