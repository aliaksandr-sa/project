package telran.java29.project.controller;

import telran.java29.project.dto.newUserDto;
import telran.java29.project.dto.userDto;
import telran.java29.project.service.RentCarService;

public class ProjectController {
	RentCarService service;
	
	public userDto RegisterNewUser(newUserDto newUser) {
		return service.addNewUser(newUser);
	}
	public userDto LoginUser() {
		return service.userLogin;
	}
	public userDto UpdateUser(newUserDto updateUser) {
		return service.userUpdate(updateUser);
	}
	public void DeleteUser() {
		return service.userDelete;
	}
}
