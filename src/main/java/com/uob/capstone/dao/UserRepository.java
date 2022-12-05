package com.uob.capstone.dao;

import org.springframework.stereotype.Repository;

import com.uob.capstone.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	@Query(value="SELECT * FROM Users u WHERE u.username = :username", nativeQuery=true)
	public User getUserByUserName(@Param("username") String username);
	
	List<User> findAllByUserRoles_NameAndEnabled(String name, boolean enabled);
}
