package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDTO {
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
}
