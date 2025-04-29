package com.bbering.picpay_desafio_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbering.picpay_desafio_backend.dtos.TransactionRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.TransactionResponseDTO;
import com.bbering.picpay_desafio_backend.services.TransactionsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Transferências", description = "Operações relacionadas à transferências")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Operation(summary = "Realizar uma nova transferência", description = "Cria uma nova transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transferência criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> postANewTransaction(
            @Valid @RequestBody TransactionRequestDTO transactionData) {
        TransactionResponseDTO transactionToReturn = transactionsService.createNewTransaction(transactionData);
        return new ResponseEntity<>(transactionToReturn, HttpStatus.CREATED);
    }
}