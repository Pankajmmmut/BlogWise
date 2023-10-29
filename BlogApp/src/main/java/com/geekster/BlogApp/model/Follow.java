package com.geekster.BlogApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne
    @JoinColumn(name = "fk_actualuser_id")
    private User currentUser;

    @ManyToOne
    @JoinColumn(name = "fk_follower_id")
    private User currentUserFollower;
}
