package com.digitalsettings.tms.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Length(min = 8, max = 64)
    private String password;
}