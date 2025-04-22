package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonUserResponseDTO {
    private Long id;
    private String fullName;
    private String CPF;
    private String email;
    private BigDecimal balance = BigDecimal.ZERO;
}
