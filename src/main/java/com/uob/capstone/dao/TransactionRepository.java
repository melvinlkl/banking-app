package com.uob.capstone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uob.capstone.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
	@Query(value = "SELECT * from transaction t where t.acc_id = :aid ORDER BY t.transaction_date_time", nativeQuery=true)
	List<Transaction> findByAccountID(@Param("aid") Long aid);
	
	@Query(value="SELECT * FROM transaction t WHERE t.acc_id = :aid and t.enabled = :enabled ORDER BY t.transaction_date_time", nativeQuery=true)
	public List<Transaction> getTransactionByIdandEnabled(@Param("aid") Long aid, @Param("enabled") boolean enabled);
	
	List<Transaction> findAllByAccount_IdAndEnabled(Long id, boolean enabled);
}