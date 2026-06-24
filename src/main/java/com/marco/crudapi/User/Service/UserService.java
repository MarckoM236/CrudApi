package com.marco.crudapi.User.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marco.crudapi.User.Dto.RequestUserDto;
import com.marco.crudapi.User.Entity.User;
import com.marco.crudapi.User.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(RequestUserDto reqUser){
        //valdate unique email
        if(userRepository.existsByEmail(reqUser.email())){

            throw new RuntimeException(
                "Email already exists"
            );
        }

        String encodedPassword = passwordEncoder.encode(reqUser.password());

        User user = new User();
        user.setName(reqUser.name());
        user.setEmail(reqUser.email());
        user.setPassword(encodedPassword);

        User saveUser = userRepository.save(user);
        return saveUser;
    }

    public Optional<User> userByEmail(String email){
        return userRepository.findByEmailContaining(email);
    }

    
    
}
