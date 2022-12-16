package org.putra.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCredentialReq {
    @NotBlank(message = "{invalid.email.required}")
    private String email;

    @NotBlank(message = "{invalid.password.required}")
    private String password;
}
