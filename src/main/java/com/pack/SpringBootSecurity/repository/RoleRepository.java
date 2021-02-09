package com.pack.SpringBootSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.SpringBootSecurity.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
}