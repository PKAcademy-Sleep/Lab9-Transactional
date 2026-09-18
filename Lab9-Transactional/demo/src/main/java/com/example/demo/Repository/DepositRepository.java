package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.DepositTransaction;


public interface DepositRepository extends JpaRepository<DepositTransaction, Long>{

}
