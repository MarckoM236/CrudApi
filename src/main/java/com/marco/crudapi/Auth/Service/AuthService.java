package com.marco.crudapi.Auth.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marco.crudapi.Auth.Dto.RequestUserAuth;
import com.marco.crudapi.User.Entity.User;
import com.marco.crudapi.User.Repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepo = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(RequestUserAuth user){
        String token = "";

        Optional<User> userFound = userRepo.findByEmailContaining(user.email());

        if(userFound.isEmpty()){
            return "prueba";
        }

        //compare password
        if(this.isPasswordValid(userFound.get().getPassword(), user.password())){
            token = jwtService.generateToken(userFound);
        }

        return token;
    }

    private boolean isPasswordValid(String hashPassword, String password){
        boolean res = false;
        if(passwordEncoder.matches(password, hashPassword)){
            res = true;
        }
        
        return res;
    }
}
