package com.edgar.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.app.ws.impl.FirebaseService;
import com.edgar.app.ws.service.UserService;
import com.edgar.app.ws.shared.dto.UserDto;
import com.edgar.app.ws.ui.model.request.UserModel;
import com.edgar.app.ws.ui.model.response.OperationStatusModel;
import com.edgar.app.ws.ui.model.response.UserCreateResponse;
import com.edgar.app.ws.ui.model.response.UserGetResponse;

@RestController // Permite que la clase actue como servlet
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("users") // http://localhost:8080/alumnos
// Very simple RESTful web service- difines CRUD operations
public class UserController {

	@Autowired // crea link(intancia) entre el servicio y el rest
	UserService userService;
	@Autowired
	FirebaseService fireBaseService;

	@GetMapping
	public List<UserGetResponse> getAllUser() {
		List<UserGetResponse> returnValue = new ArrayList<UserGetResponse>();
		List<UserDto> userDto = userService.getAll();
		System.out.println("Tamaño en control" + userDto.size());
		for (UserDto source : userDto) {
			UserGetResponse target = new UserGetResponse();
			BeanUtils.copyProperties(source, target);
			returnValue.add(target);
		}

		return returnValue;
	}

	@GetMapping(path = "/{id}")
	public UserGetResponse getUser(@PathVariable("id") int id) {
		UserGetResponse returnValue = new UserGetResponse();
		UserDto userDto = userService.getUserById(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@PostMapping
	public UserCreateResponse createAlumno(@RequestBody UserModel user)
			throws InterruptedException, ExecutionException {
		UserCreateResponse returnValue = new UserCreateResponse();
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(user, userDto);

		UserDto createdUser = userService.createUser(userDto);

		fireBaseService.createUser(userService.userId(createdUser.getEmail()), userDto);

		BeanUtils.copyProperties(createdUser, returnValue);

		return returnValue;
	}

	@PutMapping(path = "/{id}")
	public UserCreateResponse updateAlumno(@PathVariable("id") int id, @RequestBody UserModel user) {
		UserCreateResponse returnValue = new UserCreateResponse();
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(user, userDto);

		UserDto updatedUser = userService.updateUser(id, userDto);
		try {
			fireBaseService.updateUser(userService.userId(updatedUser.getEmail()), userDto);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deletUser(@PathVariable("id") int id) {

		OperationStatusModel returnValue = new OperationStatusModel();
		userService.deleteUser(id);

		fireBaseService.deleteUser(id);

		returnValue.setOperationResult("Deleted");
		returnValue.setOperationName("Delete");
		return returnValue;
	}

}