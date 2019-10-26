package telran.java29.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UpdateUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.service.UserService;

@RestController
//S
public class UserManegmentController {
	@Autowired
	UserService service;

	@PostMapping("/registration")
	public UserDto RegisterNewUser(@RequestBody NewUserDto newUser) {
		return service.addNewUser(newUser);
	}

	@GetMapping("/user/{login}")
	public UserDto LoginUser(@PathVariable String login) {
		return service.userLogin(login);
	}

	@PutMapping("/user/{id}")
	public UserDto UpdateUser(@RequestBody UpdateUserDto updateUser, @PathVariable String id) {
		return service.userUpdate(updateUser, id);
	}

	@DeleteMapping("/user")
	public void DeleteUser() {
		service.userDelete();
	}

}
