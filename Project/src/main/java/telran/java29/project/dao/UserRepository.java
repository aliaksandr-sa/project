package telran.java29.project.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Comment;
import telran.java29.project.domain.User;
//S
public interface UserRepository extends MongoRepository<User, String> {

	List<Comment> findByCommentsPostdateAfter(LocalDate date);

}
