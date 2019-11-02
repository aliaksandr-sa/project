package telran.java29.project.service;

import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
//S
public interface FindService {
	
	CarDto getCarById(String serial_number);

	Iterable<BookedPeriodDto> ownerGetBookedPeriodsByCarId(String serial_number);

	CarDto ownerGetCarById(String serial_number);
}
