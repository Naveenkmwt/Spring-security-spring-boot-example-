package com.scube.auth.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scube.auth.model.DbUser;
import com.scube.auth.model.Role;
import com.scube.auth.repository.RoleRepository;
import com.scube.auth.repository.UserRepository;
import com.scube.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(DbUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<Role>();
        Role role = roleRepository.findOne(1l);
        roleSet.add(role);
        user.setRoles(roleSet);
        user.setClientId("clientTest");
        userRepository.save(user);
    }
    
    @Override
    public void resetPassword(String username) {
    	
    	DbUser dbUser = userRepository.findByUsername(username);
    	dbUser.setPassword(bCryptPasswordEncoder.encode(dbUser.getPassword()));
      
        userRepository.save(dbUser);
    }

    @Override
    public DbUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public List<DbUser> getAllUser() {
		List<DbUser> users = userRepository.findAll();
		return users;
	}
}
