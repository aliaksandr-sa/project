package telran.java29.project.service;

import java.util.List;
import java.util.Set;

import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.dto.UpdateCarDto;
//m
public interface CarService {

	CarDto addCar(NewCarDto carDto);

	CarDto updateCar(UpdateCarDto updateCar, String serial_number);

	CarDto findCarById(String serial_number);
	
	void deleteCar();

	CarDto findOwnerCarById(String serial_number);

	List<BookedPeriod> findOwnerBookedPeriodsByCarId(String serial_number);

	ReservationResponseDto makeAReservation(ReservationDto reservationDto);

	void makeAReservation(ConfirmPaymentDto confirmPaymentDto);

	List<CarDto> get3BestBookedCars();

	Set<CommentDto> getLatestComments();

	void addAComment();

}
