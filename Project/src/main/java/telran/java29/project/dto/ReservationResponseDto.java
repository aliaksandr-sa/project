package telran.java29.project.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
//m
public class ReservationResponseDto {
	String order_number;
	Double amount;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDateTime booking_date;
	
}
