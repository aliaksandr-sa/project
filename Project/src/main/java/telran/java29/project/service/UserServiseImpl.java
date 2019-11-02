package telran.java29.project.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.User;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UpdateUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.exceptions.UserConflictException;

@Service
public class UserServiseImpl implements UserService {
//S
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;
//	@Autowired
//	PasswordEncoder passwordEncoder;
	Convertor convertor;
	
	@Override
	public UserDto addNewUser(NewUserDto newUser) {
		if (userRepository.existsById(newUser.getEmail())) {
			throw new UserConflictException();
		}
//		String hashPassword = passwordEncoder.encode(newUser.getPassword());
		User user = new User(newUser.getFirst_name(), newUser.getSecond_name(), newUser.getEmail(), newUser.getPassword());
		user = userRepository.save(user);
		return convertor.convertToUserDto(user);
	}

	@Override
	public UserDto userLogin(String login) {
		User user = userRepository.findById(login).get();
		return convertor.convertToUserDto(user);
	}

	@Override
	public UserDto userUpdate(UpdateUserDto updateUser, String id) {
		User user = userRepository.findById(id).get();
		if (updateUser.getFirst_name() != null) {
			user.setFirst_name(updateUser.getFirst_name());
		}
		if (updateUser.getSecond_name() != null) {
			user.setSecond_name(updateUser.getSecond_name());
		}
		userRepository.save(user);
		return convertor.convertToUserDto(user);
	}

	@Override
	public void userDelete(String id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);

	}

}
