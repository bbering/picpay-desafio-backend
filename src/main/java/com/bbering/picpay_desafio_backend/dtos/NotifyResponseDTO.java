package com.bbering.picpay_desafio_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyResponseDTO {
    private String status;
    private NotifyDataDTO data;
}