package com.uob.capstone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.uob.capstone.dao.RoleRepository;
import com.uob.capstone.dao.UserRepository;
import com.uob.capstone.entity.Role;
import com.uob.capstone.entity.User;

@Component    
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Override
    public void run(String...args) throws Exception {
		if (!roleRepository.existsByName("ADMIN")) {
			System.out.println("No admin");
			Role role = new Role();
			role.setName("ADMIN");
			roleRepository.save(role);
		}

		if (!roleRepository.existsByName("USER")) {
			System.out.println("No user");
			Role role = new Role();
			role.setName("USER");
			roleRepository.save(role);
		}
		List<User> uList = userRepository.findAll();
		if (uList.size() == 0) {
			Role roleAdmin1 = roleRepository.findRoleByName("ADMIN").orElseThrow();
			User user = new User();
			user.addRole(roleAdmin1);
			user.setEnabled(true);
			user.setUsername("admin");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode("admin123"));
			userRepository.save(user);
		}
    }
}