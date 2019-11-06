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
public class ReservationDto {
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDateTime start_date_time; 
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDateTime end_date_time;
	UserWhoBookedDto person_who_booked;
}
