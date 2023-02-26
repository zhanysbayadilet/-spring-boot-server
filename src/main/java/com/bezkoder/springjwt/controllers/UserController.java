package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.UserDTO;
import com.bezkoder.springjwt.models.ImageModel;
import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/user")
@Api( tags = "all")
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

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UserDTO saveUser(@RequestPart("user") UserDTO userDTO,
                         @RequestPart("imageFile") MultipartFile[] file) {
        try{
            Set<ImageModel> images = uploadImage(file);
            userDTO.setUserImages(images);
            return userDetailsService.saveUser(userDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        return ResponseEntity.ok(userDetailsService.deleteUserById(id));
    }

    // Tournaments of this User

    @GetMapping("/{user_id}/tournaments")
    public ResponseEntity<?> getUserTournaments(@PathVariable Long user_id) {
        return ResponseEntity.ok(userDetailsService.getUserTournaments(user_id));
    }

    @GetMapping("/{user_id}/myTournaments")
    public ResponseEntity<?> getMyTournaments(@PathVariable Long user_id) {
        return ResponseEntity.ok(userDetailsService.getMyTournaments(user_id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCountUsers(){
        return ResponseEntity.ok(userDetailsService.getCountUsers());
    }
}
