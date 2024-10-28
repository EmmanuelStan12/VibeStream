package com.bytebard.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.bytebard.validators.ValidPassword;
import com.bytebard.validators.ValidEmail;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    @NotEmpty(message = "Username cannot be empty")
    private String username;
}
