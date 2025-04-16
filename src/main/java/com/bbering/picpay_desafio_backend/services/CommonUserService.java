package com.bbering.picpay_desafio_backend.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbering.picpay_desafio_backend.dtos.CommonUserRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.CommonUserResponseDTO;
import com.bbering.picpay_desafio_backend.models.CommonUser;
import com.bbering.picpay_desafio_backend.repositories.CommonUserRepository;

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

}
