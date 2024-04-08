package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository ur;

    @Lazy
    @Autowired
    private PasswordEncoder pe;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.ur = userRepository;
    }

    @Override
    @Transactional
    public List<User> getListUsers() {
        return ur.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(pe.encode(user.getPassword()));
        ur.save(user);
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        return ur.getById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(pe.encode(user.getPassword()));
        ur.updateUser(user);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return ur.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        ur.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ur.findByUsername(username);
    }
}
