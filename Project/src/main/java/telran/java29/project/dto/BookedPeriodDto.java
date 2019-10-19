package telran.java29.project.dto;

import java.time.LocalDate;

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
    LocalDate start_date_time;
    LocalDate end_date_time;
    Boolean paid;
    Double amount;
    LocalDate booking_date;
    UserWhoBookedDto person_who_booked;
}
