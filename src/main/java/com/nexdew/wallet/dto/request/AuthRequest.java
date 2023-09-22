package com.nexdew.wallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 20, message = "Minimum password length: 8 characters")
    private String password;
}
