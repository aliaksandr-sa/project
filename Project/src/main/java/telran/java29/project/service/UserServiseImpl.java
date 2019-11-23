package telran.java29.project.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.User;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.UpdateUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.exceptions.ConflictException;

@Service
public class UserServiseImpl implements UserService {
//S
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	Convertor convertor;
	
	@Override
	public UserDto addNewUser(NewUserDto newUser, String token) {
		String[] credential = decodeToken(token);
		String login = credential[0];
		String password = credential[1];
		if (login == null || password == null) {
			throw new ConflictException();
		}
		if (userRepository.existsById(login)) {
			throw new ConflictException();
		}
		String hashPassword = passwordEncoder.encode(password);
		User user = new User(newUser.getFirst_name(), newUser.getSecond_name(), login, hashPassword);
		user = userRepository.save(user);
		return convertor.convertToUserDto(user);
	}

	private String[] decodeToken(String token) {
		int pos = token.indexOf(" ");
		String newToken = token.substring(pos + 1);
		byte[] decodeBytes = Base64.getDecoder().decode(newToken);
		String credential = new String(decodeBytes);
		String[] credentials = credential.split(":");
		return credentials;
	}

	@Override
	public UserDto userLogin(String login) {
		User user = userRepository.findById(login).get();
		return convertor.convertToUserDto(user);
	}

	@Override
	public UserDto userUpdate(UpdateUserDto updateUser, String id, String password) {
		User user = userRepository.findById(id).get();
		if (updateUser.getFirst_name() != null) {
			user.setFirst_name(updateUser.getFirst_name());
		}
		if (updateUser.getSecond_name() != null) {
			user.setSecond_name(updateUser.getSecond_name());
		}
		if (password != null) {
			String hashPassword = passwordEncoder.encode(password);
			user.setPassword(hashPassword);
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
