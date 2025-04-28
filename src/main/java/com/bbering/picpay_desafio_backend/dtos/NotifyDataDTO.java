package com.bbering.picpay_desafio_backend.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyDataDTO {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;

    @Override
    public String toString() {
        return String.format("Sender ID: %d, Receiver ID: %d, Amount: %.2f", senderId, receiverId, amount);
    }
}
