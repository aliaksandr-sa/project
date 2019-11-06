package telran.java29.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
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
			//(reservationDto.getStart_date_time().isBefore(bookedPeriod.getEnd_date_time())
			//&& reservationDto.getEnd_date_time().isAfter(bookedPeriod.getStart_date_time())
			if (!(checkFreePeriod(reservationDto.getStart_date_time(),reservationDto.getEnd_date_time(),
					bookedPeriod.getStart_date_time(),bookedPeriod.getEnd_date_time())
					&& !bookedPeriod.getPaid())) {
				throw new ConflictException();
			}
			//if proveryaet dostypni li dati dlya rezerva, i oplachen li bookedPeriod
			
		}

		Period period = Period.between(reservationDto.getStart_date_time().toLocalDate(),
				reservationDto.getEnd_date_time().toLocalDate().plusDays(1));
		int days = period.getDays();

		ReservationResponseDto reservationResponseDto = new ReservationResponseDto(order_number(),
				amount(car.getPrice_per_day(), days), LocalDate.now());

		BookedPeriod bookedPeriod = new BookedPeriod(reservationResponseDto.getOrder_number(),
				reservationDto.getStart_date_time(), reservationDto.getEnd_date_time(), false,
				reservationResponseDto.getAmount(), reservationResponseDto.getBooking_date(),
				convertor.convertToUser(reservationDto.getPerson_who_booked()));

		car.addBookedPeriod(bookedPeriod);

		return reservationResponseDto;
	}
//maybe here we need to stop multithreading...
	private boolean checkFreePeriod(LocalDateTime reserveStart, LocalDateTime reverveEnd, LocalDateTime bookedStart,
			LocalDateTime bookedEnd) {
		bookedEnd = bookedEnd.plusSeconds(1); //may check to minutes/days/years whatever if need
		bookedStart = bookedStart.plusSeconds(1);
		if (reserveStart.isBefore(bookedEnd)&&reverveEnd.isAfter(bookedStart)) {
			return false;
		}
		return true;
	}

	private String order_number() {
		return Long.toString(System.currentTimeMillis());
	}

	private Double amount(Double pricePerDay, int days) {
		return pricePerDay * days;
	}

	@Override
	public void makeAReservation(ConfirmPaymentDto confirmPaymentDto) {
		Car car = findCarByOrderId(confirmPaymentDto.getOrder_number());

		Set<BookedPeriod> bookedPeriods = car.getBooked_periods();
		for (BookedPeriod bookedPeriod : bookedPeriods) {
			if(bookedPeriod.getOrder_id().equals(confirmPaymentDto.getOrder_number())) {
				
				BookedPeriod bookedPeriodPaid = bookedPeriod;
				bookedPeriodPaid.setPaid(true);
				car.updateBookPeriod(bookedPeriod, bookedPeriodPaid);
			}
		}
	}

	private Car findCarByOrderId(String order_number) {
		Car neededCar = null;
		List<Car> cars = carRepository.findAll();
		for (Car car : cars) {
			Set<BookedPeriod> bookedPeriods = car.getBooked_periods();
			for (BookedPeriod bookedPeriod : bookedPeriods) {
				if (bookedPeriod.getOrder_id().equals(order_number)) {
					neededCar = car; 
					
				}
			}
		}
		return neededCar;
	}
	

}
