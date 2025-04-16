package com.bbering.picpay_desafio_backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonUserRequestDTO {

    @Size(min = 3, max = 100, message = "O nome deve possuir no mínimo 3 e no máximo 100 caracteres")
    private String fullName;

    @Size(min = 11, max = 11, message = "O CPF deve ter obrigatóriamente 11 dígitos")
    @Pattern(regexp = "^[0-9]{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    private String CPF;

    @Email(message = "Email inválido.")
    @Size(max = 256, message = "O email deve possuir, no máximo, 256 caracteres")
    private String email;

    private String password;
}
