package com.bbering.picpay_desafio_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbering.picpay_desafio_backend.dtos.CommonUserRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.CommonUserResponseDTO;
import com.bbering.picpay_desafio_backend.services.CommonUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "CommonUser", description = "Operações relacionadas ao gerenciamento de usuários do tipo CommonUser")
public class CommonUserController {

    @Autowired
    private CommonUserService commonUserService;

    @Operation(summary = "Verifica o status da API", description = "Retorna uma mensagem indicando o estado de saúde da API")
    @GetMapping("/status")
    public String healthFallback() {
        return "API is running";
    }

    @Operation(summary = "Buscar um usuário do tipo CommonUser pelo ID", description = "Retorna os dados de um usuário com base no ID fornecido")
    @GetMapping("/{id}")
    public ResponseEntity<?> findCommonUserById(@PathVariable Long id) {
        CommonUserResponseDTO cmToReturn = commonUserService.findCommonUserById(id);
        return new ResponseEntity<>(cmToReturn, HttpStatus.OK);
    }

    @Operation(summary = "Cria um novo usuário do tipo CommonUser", description = "Cadastra um novo usuário")
    @PostMapping
    public ResponseEntity<?> saveCommonUser(@Valid @RequestBody CommonUserRequestDTO cmRequest) {
        CommonUserResponseDTO cmToReturn = commonUserService.createNewCommonUser(cmRequest);
        return new ResponseEntity<>(cmToReturn, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza os dados de um usuário do tipo CommonUser", description = "Atualiza os dados de um CommonUser, fornecendo um id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommonUser(@Valid @RequestBody CommonUserRequestDTO cmRequest,
            @PathVariable Long id) {
        CommonUserResponseDTO cmToReturn = commonUserService.updateCommonUser(cmRequest, id);
        return new ResponseEntity<>(cmToReturn, HttpStatus.OK);
    }

    @Operation(summary = "Apaga os dados de um usuário do tipo CommonUser", description = "Deleta um CommonUser com base no id fornecido")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommonUser(@PathVariable Long id) {
        commonUserService.deleteCommonUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
