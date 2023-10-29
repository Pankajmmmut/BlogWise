package com.geekster.BlogApp.repository;

import com.geekster.BlogApp.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepo extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findFirstByTokenValue(String tokenValue);

}
