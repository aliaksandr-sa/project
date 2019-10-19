package telran.java29.project.controller;

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
