package telran.java29.project.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.UpdateCarDto;

@Service
//m
public class CarServiceImpl implements CarService {
	@Autowired
	CarRepository carRepository;
	Convertor convertor;

	@Override
	public CarDto addCar(NewCarDto carDto) {
		Car car = new Car(carDto.getSerial_number(), carDto.getMake(), carDto.getModel(), carDto.getYear(),
				carDto.getEngine(), carDto.getFuel(), carDto.getGear(), carDto.getWheels_drive(), carDto.getDoors(),
				carDto.getSeats(), carDto.getFuel_consumption(), carDto.getFeatures(), carDto.getCar_class(),
				carDto.getPrice_per_day(), carDto.getDistance_included(), carDto.getAbout(),
				convertor.convertToPickUpPlace(carDto.getPick_up_place()), carDto.getImage_url());
		car = carRepository.save(car);
		return convertor.convertToCarDto(car);
	}

	@Override
	public CarDto updateCar(UpdateCarDto updateCar, String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		Set<String> featurs = updateCar.getFeatures();
		if (featurs != null) {
			featurs.forEach(car::addFeature);
		}
		if (updateCar.getCar_class() != null) {
			car.setCar_class(updateCar.getCar_class());
		}
		if (updateCar.getPrice_per_day() != null) {
			car.setPrice_per_day(updateCar.getPrice_per_day());
		}
		if (updateCar.getDistance_included() != null) {
			car.setDistance_included(updateCar.getDistance_included());
		}
		if (updateCar.getPick_up_place() != null) {
			car.setPick_up_place(convertor.convertToPickupPlace(updateCar.getPick_up_place()));
		}
		Set<String> image_url = updateCar.getImage_url();
		if (image_url != null) {
			image_url.forEach(car::addImageUrl);
		}
		carRepository.save(car);
		return convertor.convertToCarDto(car);
	}

	@Override
	public void deleteCar(String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		carRepository.delete(car);
	}
}
