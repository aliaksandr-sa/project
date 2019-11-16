package telran.java29.project.service;

import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CarDtoSimple;
import telran.java29.project.dto.NewCarDto;
//m
public interface CarService {

	CarDto addCar(NewCarDto carDto, String email);

	CarDto updateCar(NewCarDto updateCar, String serial_number);

//	CarDto findCarById(String serial_number);

	void deleteCar(String serial_number);

//	CarDto findOwnerCarById(String serial_number);

//	List<BookedPeriod> findOwnerBookedPeriodsByCarId(String serial_number);
//
//	ReservationResponseDto makeAReservation(ReservationDto reservationDto);
//
//	void makeAReservation(ConfirmPaymentDto confirmPaymentDto);
//
	Iterable<CarDtoSimple> get3BestBookedCars();

//	Set<CommentDto> getLatestComments();
//
//	void addAComment();

}
