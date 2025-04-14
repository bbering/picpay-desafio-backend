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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StoreKeeperController {

    @Autowired
    private StoreKeeperService skService;

    @GetMapping("/sk/{id}")
    public ResponseEntity<?> findStoreKeeperById(@PathVariable Long id) {
        StoreKeeperResponseDTO skResponse = skService.findStoreKeeperById(id);
        return new ResponseEntity<>(skResponse, HttpStatus.OK);
    }

    @PostMapping("/sk")
    public ResponseEntity<?> createNewStoreKeeper(@Valid @RequestBody StoreKeeperRequestDTO skDTO) {
        StoreKeeperResponseDTO skResponse = skService.createStoreKeeper(skDTO);
        return new ResponseEntity<>(skResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/sk/{id}")
    public ResponseEntity<?> deleteStoreKeeper(@PathVariable Long id) {
        skService.deleteStoreKeeper(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/sk/{id}")
    public ResponseEntity<?> updateStoreKeeperData(@Valid @RequestBody StoreKeeperRequestDTO skDTO,
            @PathVariable Long id) {
        StoreKeeperResponseDTO skResponse = skService.updateStoreKeeper(skDTO, id);
        return new ResponseEntity<>(skResponse, HttpStatus.OK);
    }
}