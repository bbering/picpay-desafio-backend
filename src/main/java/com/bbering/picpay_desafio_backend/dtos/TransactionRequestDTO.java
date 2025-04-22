package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {
    
    private BigDecimal amount;

    private LocalDateTime timestamp;

    @ManyToOne
    private Long senderId;

    @ManyToOne
    private Long receiverId;
}
