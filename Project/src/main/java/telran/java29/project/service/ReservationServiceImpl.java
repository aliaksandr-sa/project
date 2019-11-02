package telran.java29.project.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import telran.java29.project.dao.CarRepository;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;

public class ReservationServiceImpl implements ReservationService {
	@Autowired
	CarRepository carRepository;
	@Override
	public ReservationResponseDto makeAReservation(ReservationDto reservationDto, String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		
		return null;
	}

	@Override
	public void makeAReservation(ConfirmPaymentDto confirmPaymentDto) {
		// TODO Auto-generated method stub

	}

}
