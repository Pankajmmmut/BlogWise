package com.geekster.BlogApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postCaption;

    private String postContent;

    private LocalDateTime postCreationTime;

    private String postLocation;

    @ManyToOne
    private User author;


    @OneToMany(mappedBy = "post")
    private List<Comment> postComments;


}