package com.uob.capstone.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name="acc_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="acc_no")
	private Long accNumber;
	
	private String accType;
	
	private Double balance;
	
	private Timestamp accDateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="account", cascade = CascadeType.ALL)
	private Set<Transaction> transactionList = new HashSet<>();
	
	private boolean enabled;
	

	public Account(Long id, Long accNumber, String accType, Double balance, Timestamp accDateTime, User user,
			Set<Transaction> transactionList, boolean enabled) {
		super();
		this.id = id;
		this.accNumber = accNumber;
		this.accType = accType;
		this.balance = balance;
		this.accDateTime = accDateTime;
		this.user = user;
		this.transactionList = transactionList;
		this.enabled = enabled;
	}


	public Account() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getAccNumber() {
		return accNumber;
	}


	public void setAccNumber(Long accNumber) {
		this.accNumber = accNumber;
	}


	public String getAccType() {
		return accType;
	}


	public void setAccType(String accType) {
		this.accType = accType;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public Timestamp getAccDateTime() {
		return accDateTime;
	}


	public void setAccDateTime(Timestamp accDateTime) {
		this.accDateTime = accDateTime;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public Set<Transaction> getTransactionList() {
		return transactionList;
	}


	public void setTransactionList(Set<Transaction> transactionList) {
		this.transactionList = transactionList;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

	
}
