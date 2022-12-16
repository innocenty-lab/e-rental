package org.putra.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.putra.entity.UserCredential;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CustomerReq {
    @NotBlank(message = "{invalid.customerName.required}")
    private String customerName;

    @NotBlank(message = "{invalid.customerAddress.required}")
    private String customerAddress;

    @NotNull(message = "{invalid.customerBirthDate.required}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date customerBirthDate;

    @NotBlank(message = "{invalid.customerPhoneNumber.required}")
    private String customerPhoneNumber;

    private UserCredential userCredential;
}
