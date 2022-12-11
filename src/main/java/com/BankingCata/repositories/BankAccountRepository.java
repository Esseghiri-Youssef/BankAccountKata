package com.BankingCata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankingCata.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,String>  {

}
