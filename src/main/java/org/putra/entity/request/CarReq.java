package org.putra.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CarReq {
    @NotBlank(message = "{invalid.policeNumber.required}")
    private String policeNumber;

    @NotBlank(message = "{invalid.carBrand.required}")
    private String carBrand;

    @NotNull(message = "{invalid.maxSeat.required}")
    private Integer maxSeat;

    @NotNull(message = "{invalid.carYear.required}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date carYear;

    @NotNull(message = "{invalid.isAvailable.required}")
    private Boolean isAvailable = false;
}
