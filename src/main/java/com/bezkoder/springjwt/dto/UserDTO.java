package com.bezkoder.springjwt.dto;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
