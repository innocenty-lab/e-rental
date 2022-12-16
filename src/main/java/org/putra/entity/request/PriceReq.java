package org.putra.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PriceReq {
    @NotNull(message = "{invalid.dailyPrice.required}")
    private Integer dailyPrice;

    private policeNumberReq car;

    @NotNull(message = "{invalid.isActive.required}")
    private Boolean isActive = false;
}
