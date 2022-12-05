package com.uob.capstone.dao;

import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uob.capstone.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	@Query("SELECT r from Role r where r.name = :name")
	Optional<Role> findRoleByName(@Param("name") String name);

	boolean existsByName(String name);
}
