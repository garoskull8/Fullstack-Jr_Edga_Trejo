package com.edgar.app.ws.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edgar.app.ws.io.entity.UserEntity;
import com.edgar.app.ws.shared.dto.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {	
	UserEntity findByEmail(String email);
	UserEntity findById(int id);
	
	
}