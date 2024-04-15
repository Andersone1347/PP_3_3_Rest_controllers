package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminRestControllers {

    private final UserService us;

    @Autowired
    public AdminRestControllers(UserService userServices) {
        this.us = userServices;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(us.getListUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(us.getUser(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/save")
    public ResponseEntity<?> addUser(@RequestBody User newUser) {
        us.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User saved");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        updateUser.setId(id);
        us.updateUser(updateUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        us.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}