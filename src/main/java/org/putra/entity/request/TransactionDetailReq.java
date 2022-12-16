package org.putra.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TransactionDetailReq {
    @NotNull(message = "{invalid.startRent.required}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startRent;

    @NotNull(message = "{invalid.endRent.required}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endRent;

    private PriceIdReq priceIdReq;
}
