package telran.java29.project.service;

import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UpdateUserDto;
import telran.java29.project.dto.UserDto;
//S
public interface UserService {

	UserDto addNewUser(NewUserDto newUser);

	UserDto userLogin(String login);

	UserDto userUpdate(UpdateUserDto updateUser, String id, String password);

	void userDelete(String id);

}
