package com.example.joaquimpinedatheironbank.http.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class MoneyTransferRequest {
    @NotNull
    @NotBlank
    private String fromAccount;
    @NotNull
    @NotBlank
    private String OwnerOfToAccount;
    @NotNull
    @NotBlank
    private String toAccount;
    @NotNull
    @NotBlank
    private BigDecimal amount;

    private String description;



}
