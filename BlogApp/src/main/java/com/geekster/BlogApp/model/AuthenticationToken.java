package com.geekster.BlogApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime creationDateTime;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User user;

    public AuthenticationToken(User user){
        this.user = user;
        this.tokenValue= UUID.randomUUID().toString();
        this.creationDateTime=LocalDateTime.now();
    }
}
