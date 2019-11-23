package telran.java29.project.service;

import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CarDtoSimple;
import telran.java29.project.dto.NewCarDto;
//m
public interface CarService {

	CarDto addCar(NewCarDto carDto, String email);

	CarDto updateCar(NewCarDto updateCar, String serial_number, String email);

	void deleteCar(String serial_number, String email);

	Iterable<CarDtoSimple> get3BestBookedCars();


}
