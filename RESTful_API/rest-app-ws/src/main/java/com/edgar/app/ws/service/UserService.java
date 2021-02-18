package com.edgar.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.edgar.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
	UserDto getUserById(int id);
	UserDto updateUser(int id, UserDto user);
	//Dto getAlumno(String email);
	void deleteUser(int id);
}
 