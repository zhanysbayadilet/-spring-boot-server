package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userDetailsService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userDetailsService.getUser(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userDetailsService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        return ResponseEntity.ok(userDetailsService.deleteUserById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCountUsers(){
        return ResponseEntity.ok(userDetailsService.getCountUsers());
    }
}
