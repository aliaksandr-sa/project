package telran.java29.project.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReservationResponseDto {
	int order_number;
	Double amount;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDate booking_date;
	
}
