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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> postANewTransaction(
            @Valid @RequestBody TransactionRequestDTO transactionData) {
        TransactionResponseDTO transactionToReturn = transactionsService.createNewTransaction(transactionData);
        return new ResponseEntity<>(transactionToReturn, HttpStatus.CREATED);
    }
}