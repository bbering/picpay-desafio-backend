package com.bbering.picpay_desafio_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class StoreKeeper extends User {

    @Column(name = "CNPJ", nullable = false, unique = true)
    @Size(min = 14, max = 14, message = "O CNPJ deve ter obrigatoriamente 14 dígitos")
    @Pattern(regexp = "^[0-9]{14}$", message = "O CNPJ deve conter exatamente 14 dígitos numéricos.")
    private String cnpj;
}
