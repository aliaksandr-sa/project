package telran.java29.project.service;

import java.util.List;

import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.UpdateCarDto;
//m
public interface CarService {

	CarDto addCar(NewCarDto carDto);

	CarDto updateCar(UpdateCarDto updateCar, String serial_number);

	CarDto findCarById(String serial_number);

	CarDto findOwnerCarById(String serial_number);

	List<BookedPeriod> findOwnerBookedPeriodsByCarId(String serial_number);

}
