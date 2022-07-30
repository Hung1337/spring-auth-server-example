package me.hung.auth.service;

import me.hung.auth.entity.UserEntity;
import me.hung.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(username);
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.singletonList(userEntity.getRole()));
    }

    public UserEntity getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserEntity getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }


}
