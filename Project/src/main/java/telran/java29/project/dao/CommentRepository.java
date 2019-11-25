package telran.java29.project.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, LocalDateTime>{

	List<Comment> findByPostdateAfter(LocalDate date);

}
