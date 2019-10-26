package telran.java29.project.controller;

import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.service.RentCarService;

public class UserManegmentController {
	RentCarService service;
	
	public UserDto RegisterNewUser(NewUserDto newUser) {
		return service.addNewUser(newUser);
	}
	public UserDto LoginUser() {
		return service.userLogin();
	}
	public UserDto UpdateUser(NewUserDto updateUser) {
		return service.userUpdate(updateUser);
	}
	public void DeleteUser() {
		return service.userDelete();
	}
	
}
