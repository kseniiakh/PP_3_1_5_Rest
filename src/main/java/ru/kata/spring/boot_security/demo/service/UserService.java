package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();

    User showById(Long userId);

    void save(User user);

    void update(User updatedUser);

    void deleteById(Long id);

    UserDetails loadUserByUsername(String username);

    User findUserByEmail(String email);
}
