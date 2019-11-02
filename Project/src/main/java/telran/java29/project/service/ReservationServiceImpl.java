package telran.java29.project.service;

import java.time.LocalDate;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import telran.java29.project.dao.CarRepository;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.exceptions.ConflictException;

public class ReservationServiceImpl implements ReservationService {
	@Autowired
	CarRepository carRepository;
	@Override
	public ReservationResponseDto makeAReservation(ReservationDto reservationDto, String serial_number) {
		Car car = carRepository.findById(serial_number).get();
	    Iterator<BookedPeriod> iterator = car.getBooked_periods().iterator();
	    BookedPeriod lastElement = iterator.next();
	    while(iterator.hasNext()) {
	        lastElement = iterator.next();
	    }
	    if (!lastElement.getPaid()||lastElement.getEnd_date_time().isAfter(LocalDate.now())) {
			throw new ConflictException();
		}
	    
		return null;
	}

	@Override
	public void makeAReservation(ConfirmPaymentDto confirmPaymentDto) {
		// TODO Auto-generated method stub

	}

}
