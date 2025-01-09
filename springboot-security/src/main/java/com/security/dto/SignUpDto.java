package com.security.dto;


import com.security.entities.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
