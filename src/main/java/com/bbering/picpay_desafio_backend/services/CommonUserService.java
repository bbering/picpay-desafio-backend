package com.bbering.picpay_desafio_backend.services;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbering.picpay_desafio_backend.dtos.CommonUserRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.CommonUserResponseDTO;
import com.bbering.picpay_desafio_backend.models.CommonUser;
import com.bbering.picpay_desafio_backend.repositories.CommonUserRepository;

import jakarta.transaction.Transactional;

@Service
public class CommonUserService {

    @Autowired
    private CommonUserRepository commonUserRepository;

    public CommonUser toEntity(CommonUserRequestDTO cmDTO) {
        CommonUser cm = new CommonUser();
        BeanUtils.copyProperties(cmDTO, cm);
        return cm;
    }

    public CommonUserResponseDTO toDTO(CommonUser cm) {
        CommonUserResponseDTO cmDTO = new CommonUserResponseDTO();
        BeanUtils.copyProperties(cm, cmDTO);
        return cmDTO;
    }

    // metodo auxiliar para validar a senha (para substituir o pattern na model)
    private boolean isPasswordValid(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password.matches(regex);
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

    public CommonUserResponseDTO findCommonUserById(Long id) {
        CommonUser foundCommonUser = commonUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum usuário encontrado com o ID"));
        CommonUserResponseDTO cmToReturn = toDTO(foundCommonUser);
        return cmToReturn;
    }

    @Transactional
    public CommonUserResponseDTO createNewCommonUser(CommonUserRequestDTO cmRequestData) {
        CommonUser cm = toEntity(cmRequestData);

        if (cmRequestData.getPassword() != null && !cmRequestData.getPassword().isEmpty()) {
            if (!isPasswordValid(cmRequestData.getPassword())) {
                throw new IllegalArgumentException("A senha fornecida é muito fraca.");
            }
            cm.setPassword(hashPassword(cm.getPassword()));
        }

        if (cm.getBalance() == null) {
            cm.setBalance(BigDecimal.ZERO);
        }

        commonUserRepository.save(cm);
        CommonUserResponseDTO cmToReturn = toDTO(cm);
        return cmToReturn;
    }

    @Transactional
    public CommonUserResponseDTO updateCommonUser(CommonUserRequestDTO cmRequestDTO, Long id) {
        CommonUser cmToUpdate = commonUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum usuário encontrado com o id!"));

        if (cmRequestDTO.getCPF() != null) {
            cmToUpdate.setCPF(cmRequestDTO.getCPF());
        }

        if (cmRequestDTO.getBalance() != null){
            cmToUpdate.setBalance(cmRequestDTO.getBalance());
        }

        if (cmRequestDTO.getEmail() != null) {
            cmToUpdate.setEmail(cmRequestDTO.getEmail());
        }

        if (cmRequestDTO.getFullName() != null) {
            cmToUpdate.setFullName(cmRequestDTO.getFullName());
        }

        if (cmRequestDTO.getPassword() != null) {
            cmToUpdate.setPassword(hashPassword(cmRequestDTO.getPassword()));
        }

        commonUserRepository.save(cmToUpdate);

        CommonUserResponseDTO cmToReturn = toDTO(cmToUpdate);
        return cmToReturn;
    }

    @Transactional
    public void deleteCommonUser(Long id) {
        commonUserRepository.deleteById(id);
    }

}