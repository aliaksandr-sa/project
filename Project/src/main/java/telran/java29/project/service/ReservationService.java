package telran.java29.project.service;

import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
//m
public interface ReservationService {

	ReservationResponseDto makeAReservation(ReservationDto reservationDto, String serial_number);

	void makeAReservation(ConfirmPaymentDto confirmPaymentDto);

}
