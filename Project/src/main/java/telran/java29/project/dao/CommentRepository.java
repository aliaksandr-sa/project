package telran.java29.project.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Comment;
//S
public interface CommentRepository extends MongoRepository<Comment, String>{
//	List<Comment> findByPost_dateAfter(LocalDate date);
}
