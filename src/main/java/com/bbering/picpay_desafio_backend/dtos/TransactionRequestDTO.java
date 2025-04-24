package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {

    private BigDecimal amount;

    private LocalDateTime timestamp;

    private Long senderId;

    private Long receiverId;

    private String receiverType;
}