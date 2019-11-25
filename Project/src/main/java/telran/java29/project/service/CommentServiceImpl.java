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
import telran.java29.project.dto.NewCommentDto;
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
	@Autowired
	Convertor convertor;

	@Override
	public Iterable<CommentDto> getLatestComments() {
//		Set<CommentDto> lastComments = 
//				carRepository.findAll().stream()
//				.map(c->c.getOwner())
//				.map(o->o.getComments())
//				.flatMap(Collection::stream)
//				.filter(c->c.getPostDate().isAfter(LocalDate.now().minusDays(6)))
//				.map(c-> convertor.convertToCommentDto(c))
//				.collect(Collectors.toSet());
//		return lastComments;
		
		return commentRepository.findByPostdateAfter(LocalDate.now().minusDays(6))
				.stream()
				.map(lc->convertor.convertToCommentDto(lc))
				.collect(Collectors.toList());
	}

	@Override
	public void addAComment(String serial_number, NewCommentDto post, String login) {
		Car car = carRepository.findById(serial_number).orElseThrow(ConflictException::new);
		User owner = car.getOwner();
		User user = userRepository.findById(login).get();
		Comment comment = new Comment(user.getFirst_name(), user.getSecond_name(), post.getPost());
		owner.addComment(comment);
		commentRepository.save(comment);
		userRepository.save(owner);
		carRepository.save(car);
	}
}
