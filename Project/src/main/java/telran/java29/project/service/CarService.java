package telran.java29.project.service;

import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.UpdateCarDto;
//m
public interface CarService {

	CarDto addCar(NewCarDto carDto);

	CarDto updateCar(UpdateCarDto updateCar, String serial_number);

//	CarDto findCarById(String serial_number);

	void deleteCar(String serial_number);

//	CarDto findOwnerCarById(String serial_number);

//	List<BookedPeriod> findOwnerBookedPeriodsByCarId(String serial_number);
//
//	ReservationResponseDto makeAReservation(ReservationDto reservationDto);
//
//	void makeAReservation(ConfirmPaymentDto confirmPaymentDto);
//
	Iterable<CarDto> get3BestBookedCars();

//	Set<CommentDto> getLatestComments();
//
//	void addAComment();

}
