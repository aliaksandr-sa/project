package telran.java29.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.CarDto;

@Service
public class FindServiceImpl implements FindService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;
	CarServiceImpl carService;
	@Override
	public CarDto getCarById(String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		return carService.convertToCarDto(car);
	}

	@Override
	public List<BookedPeriod> findOwnerBookedPeriodsByCarId(String serial_number) {
		// TODO Auto-generated method stub
		return null;
	}

}
