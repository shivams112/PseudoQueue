package com.natwestgroup.challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pseudo_transaction")
public class Transaction {

@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="account_number")
private String accountNumber;

@Column(name="type")
private String type;

@Column(name="amount")
private String amount;

@Column(name="currency")
private String currency;

@Column(name="account_from")
private String accountFrom;

public Transaction() {
	
}

public Transaction(int id, String accountNumber, String type, String amount, String currency, String accountFrom) {
	super();
	this.id = id;
	this.accountNumber = accountNumber;
	this.type = type;
	this.amount = amount;
	this.currency = currency;
	this.accountFrom = accountFrom;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getAmount() {
	return amount;
}

public void setAmount(String amount) {
	this.amount = amount;
}

public String getCurrency() {
	return currency;
}

public void setCurrency(String currency) {
	this.currency = currency;
}

public String getAccountFrom() {
	return accountFrom;
}

public void setAccountFrom(String accountFrom) {
	this.accountFrom = accountFrom;
}

@Override
public String toString() {
	return "Transaction [id=" + id + ", accountNumber=" + accountNumber + ", type=" + type + ", amount=" + amount
			+ ", currency=" + currency + ", accountFrom=" + accountFrom + "]";
}




}
