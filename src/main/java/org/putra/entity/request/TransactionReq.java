package org.putra.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class TransactionReq {
    @NotNull(message = "{invalid.bookedDate.required}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookedDate;

    @NotNull(message = "{invalid.isPaid.required}")
    private Boolean isPaid = false;

    @NotNull(message = "{invalid.paidDate.required}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paidDate;

    private List<TransactionDetailReq> transactionDetails;
}
