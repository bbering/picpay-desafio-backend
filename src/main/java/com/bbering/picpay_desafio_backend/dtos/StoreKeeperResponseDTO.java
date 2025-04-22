package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreKeeperResponseDTO {
    private Long id;
    private String fullName;
    private String Cnpj;
    private String email;
    private BigDecimal balance = BigDecimal.ZERO;;
}
