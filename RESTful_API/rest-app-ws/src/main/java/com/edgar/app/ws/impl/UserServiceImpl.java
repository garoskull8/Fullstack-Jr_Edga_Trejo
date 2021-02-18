package com.edgar.app.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edgar.app.ws.io.entity.UserEntity;
import com.edgar.app.ws.io.repositories.UserRepository;
import com.edgar.app.ws.service.UserService;
import com.edgar.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserDto> getAll() {
		
		List<UserEntity> userEntity = userRepository.findAll();
		if(userEntity.size()>0) {
		System.out.println("Tamaño"+userEntity.size());
		List<UserDto> returnValue= new ArrayList<UserDto>();
		for (UserEntity source: userEntity ) {
	        UserDto target= new UserDto();
	        BeanUtils.copyProperties(source , target);
	        returnValue.add(target);
	     }
		return returnValue;
		}else {
			throw new RuntimeException("Esta vacio");

		}
	}
	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("ya existe");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		UserEntity detallesAlmacenados = userRepository.saveAndFlush(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(detallesAlmacenados, returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(int id) {

		UserEntity userEntity = userRepository.findById(id);

		if (userEntity == null)
			throw new RuntimeException("No existe");

		userRepository.delete(userEntity);

	}

	@Override
	public UserDto getUserById(int id) {

		UserEntity userEntity = userRepository.findById(id);

		if (userEntity == null)
			throw new RuntimeException("No existe");

		UserEntity detallesAlmacenados = userEntity;
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(detallesAlmacenados, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(int id,UserDto user) {
		UserEntity userEntity = userRepository.findById(id);

		if (userEntity == null)
			throw new RuntimeException("No existe");

		userEntity.setNombre(user.getNombre());
		userEntity.setAp(user.getAp());
		userEntity.setAm(user.getAm());
		userEntity.setEmail(user.getEmail());
		userEntity.setCalle(user.getCalle());
		userEntity.setNumero(user.getNumero());
		userEntity.setMunicipio(user.getMunicipio());
		userEntity.setEstado(user.getEstado());
		userEntity.setTelefono(user.getTelefono());
		

		UserEntity detallesAlmacenados = userRepository.saveAndFlush(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(detallesAlmacenados, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int userId(String email) {
		UserEntity userEntity=userRepository.findByEmail(email);
		
		if (userRepository.findByEmail(email) == null)
			throw new RuntimeException("No existe");
		
		// TODO Auto-generated method stub
		return userEntity.getId_user();
	}

	

}
