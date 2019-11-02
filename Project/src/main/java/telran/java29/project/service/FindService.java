package telran.java29.project.service;

import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.OwnCarDto;

//S
public interface FindService {

	CarDto getCarById(String serial_number);

	Iterable<OwnCarDto> ownerGetCars(String id);

	OwnCarDto ownerGetCarById(String serial_number);

	Iterable<BookedPeriodDto> ownerGetBookedPeriodsByCarId(String serial_number);

}
