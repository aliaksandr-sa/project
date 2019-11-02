package telran.java29.project.service;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.exceptions.ConflictException;
//m
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	CarRepository carRepository;
	Convertor convertor;
	@Override
	public ReservationResponseDto makeAReservation(ReservationDto reservationDto, String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		Set<BookedPeriod> bookedPeriods = car.getBooked_periods();
	    for (BookedPeriod bookedPeriod : bookedPeriods) {
			if (reservationDto.getStart_date_time().isBefore(bookedPeriod.getEnd_date_time())&&
				reservationDto.getEnd_date_time().isAfter(bookedPeriod.getStart_date_time())){
				throw new ConflictException();
			}
		}
	    ReservationResponseDto reservationResponseDto = new ReservationResponseDto(order_number, amount, LocalDate.now());
	    BookedPeriod bookedPeriod = new BookedPeriod(reservationResponseDto.getOrder_number(),
	    		reservationDto.getStart_date_time(), reservationDto.getEnd_date_time(), false,
	    		reservationResponseDto.getAmount(), reservationResponseDto.getBooking_date(),
	    		convertor.convertToUser(reservationDto.getPerson_who_booked());
	    car.addBookedPeriod(bookedPeriod);
		return reservationResponseDto;
	}

	@Override
	public void makeAReservation(ConfirmPaymentDto confirmPaymentDto) {
		// TODO Auto-generated method stub

	}

}
