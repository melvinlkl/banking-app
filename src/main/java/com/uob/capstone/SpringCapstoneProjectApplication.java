package com.uob.capstone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uob.capstone.dao.RoleRepository;
import com.uob.capstone.dao.UserRepository;
import com.uob.capstone.entity.Role;
import com.uob.capstone.entity.User;

@SpringBootApplication
public class SpringCapstoneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCapstoneProjectApplication.class, args);
	}


}
