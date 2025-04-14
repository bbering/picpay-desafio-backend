package com.bbering.picpay_desafio_backend.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbering.picpay_desafio_backend.dtos.StoreKeeperRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.StoreKeeperResponseDTO;
import com.bbering.picpay_desafio_backend.models.StoreKeeper;
import com.bbering.picpay_desafio_backend.repositories.StoreKeeperRepository;

@Service
public class StoreKeeperService {

    @Autowired
    private StoreKeeperRepository storeKeeperRepository;

    public StoreKeeper toEntity(StoreKeeperRequestDTO skDTO) {
        StoreKeeper sk = new StoreKeeper();
        BeanUtils.copyProperties(skDTO, sk);
        return sk;
    }

    public StoreKeeperResponseDTO toDTO(StoreKeeper sk) {
        StoreKeeperResponseDTO skDTO = new StoreKeeperResponseDTO();
        BeanUtils.copyProperties(sk, skDTO);
        return skDTO;
    }

    // metodo auxiliar para gerar um hash simples para a senha
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao aplicar hash na senha", e);
        }
    }

    public StoreKeeperResponseDTO findStoreKeeperById(Long id) {
        StoreKeeper foundStoreKeeper = storeKeeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum lojista encontrado!"));

        StoreKeeperResponseDTO skToReturn = toDTO(foundStoreKeeper);
        return skToReturn;
    }

    @Transactional
    public StoreKeeperResponseDTO createStoreKeeper(StoreKeeperRequestDTO skRequestDTO) {
        StoreKeeper sk = toEntity(skRequestDTO);

        if (skRequestDTO.getPassword() != null && !skRequestDTO.getPassword().isEmpty()) {
            if (!isPasswordValid(skRequestDTO.getPassword())) {
                throw new IllegalArgumentException("A senha fornecida é muito fraca.");
            }

            sk.setPassword(hashPassword(skRequestDTO.getPassword()));
        }

        storeKeeperRepository.save(sk);
        StoreKeeperResponseDTO skResponseDTO = toDTO(sk);
        return skResponseDTO;
    }

    // metodo auxiliar para validar a senha (para substituir o pattern na model)
    private boolean isPasswordValid(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password.matches(regex);
    }

    @Transactional
    public StoreKeeperResponseDTO updateStoreKeeper(StoreKeeperRequestDTO skRequestDTO, Long id) {
        StoreKeeper skToUpdate = storeKeeperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum lojista encontrado com o id"));

        if (skRequestDTO.getCnpj() != null) {
            skToUpdate.setCnpj(skRequestDTO.getCnpj());
        }
        if (skRequestDTO.getEmail() != null) {
            skToUpdate.setEmail(skRequestDTO.getEmail());
        }
        if (skRequestDTO.getFullName() != null) {
            skToUpdate.setFullName(skRequestDTO.getFullName());
        }
        if (skRequestDTO.getPassword() != null && !skRequestDTO.getPassword().isEmpty()) {
            skToUpdate.setPassword(hashPassword(skRequestDTO.getPassword()));
        }

        StoreKeeperResponseDTO skToReturn = toDTO(skToUpdate);
        return skToReturn;
    }

    @Transactional
    public void deleteStoreKeeper(Long id) {
        storeKeeperRepository.deleteById(id);
    }
}