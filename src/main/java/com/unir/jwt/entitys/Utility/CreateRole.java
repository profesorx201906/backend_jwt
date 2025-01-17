package com.unir.jwt.entitys.Utility;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unir.jwt.entitys.ERole;
import com.unir.jwt.entitys.Role;
import com.unir.jwt.entitys.User;
import com.unir.jwt.repository.RoleRepository;
import com.unir.jwt.repository.UserRepository;

@Component
public class CreateRole implements CommandLineRunner {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    if (roleRepository.findAll().size() == 0) {
      Role rolAdmin = new Role(ERole.ROLE_ADMIN);
      Role rolUser = new Role(ERole.ROLE_USER);
      Role rolModerator = new Role(ERole.ROLE_MODERATOR);
      roleRepository.save(rolAdmin);
      roleRepository.save(rolUser);
      roleRepository.save(rolModerator);
    }

    if (userRepository.findAll().size() == 0) {
      Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();
      Set<Role> roles = new HashSet<>();
      roles.add(userRole);
      User usuario = new User();
      usuario.setUsername("admin");
      usuario.setEmail("admin@unir.net");
      usuario.setPassword("$2a$10$YPfYD7zPaMCOuiyI3Jyi9egWnF5DJLNqxmXzd2vQG1oa2ZA7q5q1O");
      usuario.setRoles(roles);
      userRepository.save(usuario);
    }

  }
}
