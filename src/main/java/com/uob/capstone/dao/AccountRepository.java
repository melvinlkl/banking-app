package com.uob.capstone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uob.capstone.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value = "SELECT * FROM ACCOUNT a where a.user_id = :user_id", nativeQuery = true)
	List<Account> findByUser(@Param("user_id") Long user_id);

	@Query(value = "SELECT SUM(a.balance) FROM ACCOUNT a GROUP BY a.user_id HAVING user_id = :user_id", nativeQuery = true)
	Float findTotalBalByCustomer(@Param("user_id") Long user_id);

	@Query(value="SELECT COUNT(*) FROM ACCOUNT a WHERE a.user_id = :user_id", nativeQuery = true)
	Integer countAccByUserID(@Param("user_id") Long user_id);

	@Transactional
	@Modifying
	@Query(value = "update Account a set a.balance = :balance where a.acc_id = :aid", nativeQuery = true)
	void setBalanceById(@Param("balance") Double balance, @Param("aid") Long aid);

	@Query(value = "SELECT * FROM ACCOUNT a where a.acc_no = :acc_no", nativeQuery = true)
	Account findAccountByAccNo(@Param("acc_no") Long acc_no);

	@Query(value = "SELECT * FROM Account a WHERE a.user_id = :id and a.enabled = :enabled ORDER BY a.acc_date_time", nativeQuery = true)
	public List<Account> getAccountByIdandEnabled(@Param("id") Long id, @Param("enabled") boolean enabled);

	List<Account> findAllByUser_UseridAndEnabled(Long id, boolean enabled);
}
