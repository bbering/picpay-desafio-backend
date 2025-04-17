package com.bbering.picpay_desafio_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbering.picpay_desafio_backend.dtos.CommonUserResponseDTO;
import com.bbering.picpay_desafio_backend.services.CommonUserService;

@RestController
@RequestMapping("/api/v1")
public class CommonUserController {

    private CommonUserService commonUserService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findCommonUserById(@PathVariable Long id) {
        CommonUserResponseDTO cmToReturn = commonUserService.findCommonUserById(id);
        return new ResponseEntity<>(cmToReturn, HttpStatus.OK);
    }

}
