package com.bbering.picpay_desafio_backend.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbering.picpay_desafio_backend.dtos.TransactionRequestDTO;
import com.bbering.picpay_desafio_backend.dtos.TransactionResponseDTO;
import com.bbering.picpay_desafio_backend.models.CommonUser;
import com.bbering.picpay_desafio_backend.models.StoreKeeper;
import com.bbering.picpay_desafio_backend.models.Transactions;
import com.bbering.picpay_desafio_backend.repositories.CommonUserRepository;
import com.bbering.picpay_desafio_backend.repositories.StoreKeeperRepository;
import com.bbering.picpay_desafio_backend.repositories.TransactionsRepository;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private CommonUserRepository commonUserRepository;

    @Autowired
    private StoreKeeperRepository storeKeeperRepository;

    public Transactions toEntity(TransactionRequestDTO trnRequestDTO) {
        Transactions transaction = new Transactions();
        BeanUtils.copyProperties(trnRequestDTO, transaction);

        CommonUser sender = commonUserRepository.findById(trnRequestDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Nenhum usuário encontrado com o id"));

        StoreKeeper receiver = storeKeeperRepository.findById(trnRequestDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Nenhum lojista encontrado com o id"));

        transaction.setSender(sender);
        transaction.setReceiver(receiver);

        return transaction;
    }

    public TransactionResponseDTO toDTO(Transactions transaction) {
        TransactionResponseDTO tResponseDTO = new TransactionResponseDTO();
        BeanUtils.copyProperties(transaction, tResponseDTO);

        tResponseDTO.setReceiverId(transaction.getReceiver().getId());
        tResponseDTO.setSenderId(transaction.getSender().getId());

        tResponseDTO.setReceiverName(transaction.getReceiver().getFullName());
        tResponseDTO.setSenderName(transaction.getSender().getFullName());

        return tResponseDTO;
    }

}
