package telran.java29.project.service;

import java.util.List;

import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.dto.CarDto;
//S
public interface FindService {
	
	CarDto getCarById(String serial_number);

	List<BookedPeriod> ownerGetBookedPeriodsByCarId(String serial_number);
}
