package telran.java29.project.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.OwnCarDto;
//S
@Service
public class FindServiceImpl implements FindService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	Convertor convertor;

	@Override
	public CarDto getCarById(String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		return convertor.convertToCarDto(car);
	}

	@Override
	public Iterable<OwnCarDto> ownerGetCars(String id) {
		Set<Car> cars = userRepository.findById(id).get().getOwn_cars();
		return cars.stream().map(c -> convertor.convertToOwnCarDto(c)).collect(Collectors.toSet());
	}

	@Override
	public OwnCarDto ownerGetCarById(String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		return convertor.convertToOwnCarDto(car);
	}

	@Override
	public Iterable<BookedPeriodDto> ownerGetBookedPeriodsByCarId(String serial_number) {
		Set<BookedPeriod> bookedPeriods = carRepository.findById(serial_number).get().getBooked_periods();
		return bookedPeriods.stream().map(bp -> convertor.convertToBookedPeriodDto(bp)).collect(Collectors.toSet());
	}

}
