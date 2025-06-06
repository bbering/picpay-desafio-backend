package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreKeeperRequestDTO {

    @Size(min = 3, max = 100, message = "O nome deve possuir no mínimo 3 e no máximo 100 caracteres")
    private String fullName;

    @Size(min = 14, max = 14, message = "O CNPJ deve ter obrigatoriamente 14 dígitos")
    @Pattern(regexp = "^[0-9]{14}$", message = "O CNPJ deve conter exatamente 14 dígitos numéricos.")
    private String Cnpj;

    @Email(message = "O email deve ser válido")
    @Size(max = 256, message = "O email deve possuir, no máximo, 256 caracteres")
    private String email;

    private String password;

    private BigDecimal balance = BigDecimal.ZERO;
}