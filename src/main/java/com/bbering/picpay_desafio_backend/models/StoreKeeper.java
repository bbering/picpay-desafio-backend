package com.bbering.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "storeKeepers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class StoreKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 100, message = "O nome deve possuir no mínimo 3 e no máximo 100 caracteres")
    private String fullName;

    @Column(name = "CNPJ", nullable = false, unique = true)
    @Size(min = 14, max = 14, message = "O CNPJ deve ter obrigatoriamente 14 dígitos")
    @Pattern(regexp = "^[0-9]{14}$", message = "O CNPJ deve conter exatamente 14 dígitos numéricos.")
    private String CNPJ;

    @Column(name = "email", nullable = false, unique = true)
    @Size(max = 256, message = "O email deve possuir, no máximo, 256 caracteres")
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 20, message = "A senha deve possuir entre 8 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.")
    private String password;

}
