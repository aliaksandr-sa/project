package telran.java29.project.service;

import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.UpdateCarDto;
//m
public interface CarService {

	CarDto addCar(NewCarDto carDto);

	CarDto updateCar(UpdateCarDto updateCar, String serial_number);

	CarDto findCarById(String serial_number);

}
