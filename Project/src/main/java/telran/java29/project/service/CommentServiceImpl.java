package telran.java29.project.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.CommentRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.Car;
import telran.java29.project.domain.Comment;
import telran.java29.project.domain.User;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.exceptions.BadDateFormatException;
import telran.java29.project.exceptions.ConflictException;
//S
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	CommentRepository commentRepository;
	Convertor convertor;

	@Override
	public Iterable<CommentDto> getLatestComments() {
		try {
			return commentRepository.findByPost_dateAfter(LocalDate.now().minusDays(1)).stream()
					.map(c -> convertor.convertToCommentDto(c)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new BadDateFormatException();
		}
		
	}

	@Override
	public void addAComment(String serial_number, String post) {
		Car car = carRepository.findById(serial_number).orElseThrow(ConflictException::new);
		User user = car.getOwner();
		Comment comment = new Comment(user.getFirst_name(), user.getSecond_name(), post);
		user.addComment(comment);
		userRepository.save(user);
		commentRepository.save(comment);
	}
}
