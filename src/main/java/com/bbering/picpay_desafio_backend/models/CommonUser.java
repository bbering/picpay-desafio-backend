package com.bbering.picpay_desafio_backend.models;

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
@Table(name = "commonUsers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class CommonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 100, message = "O nome deve possuir no mínimo 3 e no máximo 100 caracteres")
    private String fullName;

    @Column(name = "CPF", nullable = false, unique = true)
    @Size(min = 11, max = 11, message = "O CPF deve ter obrigatóriamente 11 dígitos")
    @Pattern(regexp = "^[0-9]{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    private String CPF;

    @Column(name = "email", nullable = false, unique = true)
    @Size(max = 256, message = "O email deve possuir, no máximo, 256 caracteres")
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 20, message = "A senha deve possuir entre 8 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.")
    private String password;

}