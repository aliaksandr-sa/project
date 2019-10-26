package telran.java29.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.User;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.exceptions.UserConflictException;

public class UserServiseImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDto addNewUser(NewUserDto newUser) {
		if (userRepository.existsById(newUser.getEmail())) {
			throw new UserConflictException();
		}
		User user = new User(newUser.getFirst_name(), newUser.getSecond_name(), newUser.getEmail(), newUser.getPassword());
		user = userRepository.save(user);
		return null;
	}
 
	
	@Override
	public UserDto userLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto userUpdate(NewUserDto updateUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void userDelete() {
		// TODO Auto-generated method stub

	}

}
