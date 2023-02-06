package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> showAll() {
        return userRepository.findAll();
    }


    public User show(Long userId) {

        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }


    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }


    public void update(User updatedUser) {

        updatedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);

    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
