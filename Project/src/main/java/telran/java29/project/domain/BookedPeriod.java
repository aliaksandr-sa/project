package telran.java29.project.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java29.project.dto.UserWhoBookedDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"order_id"})
public class BookedPeriod {
	String order_id;
    LocalDate start_date_time;
    LocalDate end_date_time;
    Boolean paid;
    Double amount;
    LocalDate booking_date;
    UserWhoBookedDto person_who_booked;
}
