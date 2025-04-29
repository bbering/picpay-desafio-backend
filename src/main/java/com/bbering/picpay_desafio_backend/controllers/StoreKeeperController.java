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

import com.bbering.picpay_desafio_backend.dtos.StoreKeeperRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.StoreKeeperResponseDTO;
import com.bbering.picpay_desafio_backend.services.StoreKeeperService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/storekeepers")
@Tag(name = "StoreKeeper", description = "Operações relacionadas ao gerenciamento de usuários do tipo StoreKeeper")
public class StoreKeeperController {

    @Autowired
    private StoreKeeperService skService;

    @Operation(summary = "Busca por um usuário com base no ID fornecido", description = "Retorna um usuário do tipo StoreKeeper")
    @GetMapping("/{id}")
    public ResponseEntity<?> findStoreKeeperById(@PathVariable Long id) {
        StoreKeeperResponseDTO skResponse = skService.findStoreKeeperById(id);
        return new ResponseEntity<>(skResponse, HttpStatus.OK);
    }

    @Operation(summary = "Cria um novo usuário", description = "Cria um usuário do tipo StoreKeeper")
    @PostMapping()
    public ResponseEntity<?> createNewStoreKeeper(@Valid @RequestBody StoreKeeperRequestDTO skDTO) {
        StoreKeeperResponseDTO skResponse = skService.createStoreKeeper(skDTO);
        return new ResponseEntity<>(skResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Apaga os dados de um usuário", description = "Apaga os dados de um usuário do tipo StoreKeeper com base no seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStoreKeeper(@PathVariable Long id) {
        skService.deleteStoreKeeper(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Altera os dados de um usuário", description = "Atualiza os dados de um usuário do tipo StoreKeeper com base no ID fornecido")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStoreKeeperData(@Valid @RequestBody StoreKeeperRequestDTO skDTO,
            @PathVariable Long id) {
        StoreKeeperResponseDTO skResponse = skService.updateStoreKeeper(skDTO, id);
        return new ResponseEntity<>(skResponse, HttpStatus.OK);
    }
}