package com.bbering.picpay_desafio_backend.dtos;

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
    private String CNPJ;

    @Size(max = 256, message = "O email deve possuir, no máximo, 256 caracteres")
    private String email;

    @Size(min = 8, max = 20, message = "A senha deve possuir entre 8 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.")
    private String password;

}
