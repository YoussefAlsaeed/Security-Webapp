package com.secapp.springjwt.services;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.secapp.springjwt.models.ERole;
import com.secapp.springjwt.models.Role;
import com.secapp.springjwt.models.User;
import com.secapp.springjwt.repository.RoleRepository;
import com.secapp.springjwt.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	public List<User> getAllUsers() {
	    return userRepository.findByRoleUser(ERole.ROLE_USER);
	}

	public boolean assignAdminRoleToUser(Long userId) {
	    Optional<User> userOptional = userRepository.findById(userId);
	    if (userOptional.isPresent()) {
	      User user = userOptional.get();
	      Optional<Role> roleOptional = roleRepository.findByName(ERole.ROLE_ADMIN);
	      if (roleOptional.isPresent()) {
	        Role adminRole = roleOptional.get();
	        Set<Role> roles = user.getRoles();
	        roles.add(adminRole);
	        user.setRoles(roles);
	        userRepository.save(user);
	        return true;
	      }
	    }
	    return false;
	  }
}