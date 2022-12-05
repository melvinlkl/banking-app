package com.uob.capstone.entity;

import java.sql.Timestamp;


import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@Column (name = "transaction_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionNo;
	
	private String transactionType;
	
	private String transactionStatus;
	
	private Double transactionAmount;
	
	private Timestamp transactionDateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acc_id")
	private Account account;
	
	private boolean enabled;


	
	public Transaction(Long transactionNo, String transactionType, String transactionStatus, Double transactionAmount,
			Timestamp transactionDateTime, Account account, boolean enabled) {
		super();
		this.transactionNo = transactionNo;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.transactionAmount = transactionAmount;
		this.transactionDateTime = transactionDateTime;
		this.account = account;
		this.enabled = enabled;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(Long transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Timestamp getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}
