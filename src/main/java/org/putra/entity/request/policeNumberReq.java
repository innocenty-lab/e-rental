package org.putra.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class policeNumberReq {
    @NotBlank(message = "{invalid.policeNumber.required}")
    private String policeNumber;
}
