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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CommonUserController {

    @Autowired
    private CommonUserService commonUserService;

    @GetMapping("/status")
    public String healthFallback(){
        return "API is running";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCommonUserById(@PathVariable Long id) {
        CommonUserResponseDTO cmToReturn = commonUserService.findCommonUserById(id);
        return new ResponseEntity<>(cmToReturn, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCommonUser(@Valid @RequestBody CommonUserRequestDTO cmRequest) {
        CommonUserResponseDTO cmToReturn = commonUserService.createNewCommonUser(cmRequest);
        return new ResponseEntity<>(cmToReturn, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommonUser(@Valid @RequestBody CommonUserRequestDTO cmRequest,
            @PathVariable Long id) {
        CommonUserResponseDTO cmToReturn = commonUserService.updateCommonUser(cmRequest, id);
        return new ResponseEntity<>(cmToReturn, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommonUser(@PathVariable Long id) {
        commonUserService.deleteCommonUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
