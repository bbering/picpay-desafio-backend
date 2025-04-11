package com.bbering.picpay_desafio_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoreKeeperResponseDTO {
    private Long id;
    private String fullName;
    private String CNPJ;
    private String email;
}
