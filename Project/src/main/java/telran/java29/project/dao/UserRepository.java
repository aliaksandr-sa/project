package telran.java29.project.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.User;
//S
public interface UserRepository extends MongoRepository<User, String> {

}
