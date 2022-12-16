package org.putra.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PriceIdReq {
    @NotBlank(message = "{invalid.priceId.required}")
    private String priceId;
}
