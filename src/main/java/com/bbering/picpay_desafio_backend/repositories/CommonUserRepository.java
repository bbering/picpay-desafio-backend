package com.bbering.picpay_desafio_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbering.picpay_desafio_backend.models.CommonUser;

public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {

}
