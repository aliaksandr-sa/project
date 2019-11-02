package telran.java29.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
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
	Convertor convertor;
	@Override
	public CarDto getCarById(String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		return convertor.convertToCarDto(car);
	}

	@Override
	public List<BookedPeriod> ownerGetBookedPeriodsByCarId(String serial_number) {
		Set<BookedPeriod> bookedPeriods = carRepository.findById(serial_number).get().getBooked_periods();
		return null;
	}

}
