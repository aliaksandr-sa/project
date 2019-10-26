package telran.java29.project.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Car;
import telran.java29.project.dto.CarDto;

public interface CarRepository extends MongoRepository<Car, String> {
	
	CarDto findCarById(String serial_number);
}
