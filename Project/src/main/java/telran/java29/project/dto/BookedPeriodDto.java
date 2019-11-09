package telran.java29.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookedPeriodDto {
	String order_id;
    LocalDateTime start_date_time;
    LocalDateTime end_date_time;
    Boolean paid;
    Double amount;
    LocalDateTime booking_date;
    UserWhoBookedDto person_who_booked;
}
