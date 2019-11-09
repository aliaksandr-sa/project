package telran.java29.project.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Car;
import telran.java29.project.dto.BookedPeriodDto;

public interface CarRepository extends MongoRepository<Car, String> {

//	Iterable<BookedPeriodDto> findBooked_periodsBySerial_number(String serial_number);
	
//	CarDto findCarById(String serial_number);
}
