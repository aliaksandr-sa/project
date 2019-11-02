package telran.java29.project.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Car;

public interface CarRepository extends MongoRepository<Car, String> {
	
//	CarDto findCarById(String serial_number);
}
