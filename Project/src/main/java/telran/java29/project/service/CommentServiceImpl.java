package telran.java29.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.Car;
import telran.java29.project.domain.Comment;
import telran.java29.project.domain.User;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.exceptions.ConflictException;

public class CommentServiceImpl implements CommentService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;
	Convertor convertor;

	@Override
	public Iterable<CommentDto> getLatestComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAComment(String serial_number, String post) {
		Car car = carRepository.findById(serial_number).orElseThrow(ConflictException::new);
		User user = car.getOwner();
		user.addComment(new Comment(user.getFirst_name(), user.getSecond_name(), post));
		userRepository.save(user);
	}
}
