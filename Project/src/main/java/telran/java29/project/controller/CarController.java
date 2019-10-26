package telran.java29.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.UpdateCarDto;
import telran.java29.project.service.CarService;
@RestController
//m
public class CarController {
	@Autowired
	CarService carService;
	
	@PostMapping("/car")
	public CarDto addCar(@RequestBody NewCarDto newCar) {
		return carService.addCar(newCar);
	}
	
	@PutMapping("/car/{serial_number}")
	public CarDto updateCar(@RequestBody UpdateCarDto updateCar, @PathVariable String serial_number) {
		return carService.updateCar(updateCar, serial_number);
	}
	
	@DeleteMapping("/car/{serial_number}")
	public void deleteCar(String serial_number) {
	}
	
	@GetMapping("/car/{serial_number}")
	public CarDto getCarById(@PathVariable String serial_number) {
		return carService.findCarById(serial_number);
		//TODO in implementation not to give full view of bookedPeriodDto! 
	}
	
	@GetMapping("/user/cars/{serial_number}")
	public CarDto ownerGetCarById(@PathVariable String serial_number) {
		return carService.findOwnerCarById(serial_number);
	}
	
	@GetMapping("/user/cars/{serial_number}/periods")
	public List<BookedPeriod> ownerGetBookedPeriodsByCarId(@PathVariable String serial_number){
		return carService.findOwnerBookedPeriodsByCarId(serial_number);
	}
	
	
	
	
	
	
	
	
	
	
	
}
