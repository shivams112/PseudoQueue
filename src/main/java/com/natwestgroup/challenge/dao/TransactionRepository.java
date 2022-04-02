package com.natwestgroup.challenge.dao;

import org.springframework.data.repository.CrudRepository;

import com.natwestgroup.challenge.entities.Transaction;


public interface TransactionRepository extends CrudRepository<Transaction, String> {

}
