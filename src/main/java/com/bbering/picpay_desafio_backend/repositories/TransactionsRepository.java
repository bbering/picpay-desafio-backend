package com.bbering.picpay_desafio_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bbering.picpay_desafio_backend.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

}
