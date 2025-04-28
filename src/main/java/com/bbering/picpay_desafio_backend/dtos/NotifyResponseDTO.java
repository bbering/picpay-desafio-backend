package com.bbering.picpay_desafio_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyResponseDTO {
    private String status;
    private NotifyDataDTO data;

    @Override
    public String toString() {
        return String.format("Status: %s, Data: {%s}", status, data != null ? data.toString() : "No data");
    }
}