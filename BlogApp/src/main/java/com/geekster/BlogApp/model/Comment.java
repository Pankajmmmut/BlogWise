package com.geekster.BlogApp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank
    private String commentBody;

    private LocalDateTime commentCreationTime;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;


}