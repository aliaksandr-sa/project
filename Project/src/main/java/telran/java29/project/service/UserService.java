package telran.java29.project.service;

import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UserDto;

public interface UserService {

	UserDto addNewUser(NewUserDto newUser);

	UserDto userLogin(String login);

	UserDto userUpdate(NewUserDto updateUser);

	void userDelete();

}
