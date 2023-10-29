package com.geekster.BlogApp.service;

import com.geekster.BlogApp.model.AuthenticationToken;
import com.geekster.BlogApp.repository.IAuthRepo;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    IAuthRepo authRepo;

    public boolean authenticate(String email,String tokenValue) {

        AuthenticationToken token =  authRepo.findFirstByTokenValue(tokenValue);
        if(token==null)
        {
            return false;
        }
        else
        {
            return token.getUser().getUserEmail().equals(email);
        }
    }
}
