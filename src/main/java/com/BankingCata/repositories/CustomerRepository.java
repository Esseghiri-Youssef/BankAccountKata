package com.BankingCata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankingCata.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
