package telran.java29.project.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"order_id"})
//S
public class BookedPeriod {
	@Id
	String order_id;
    LocalDate start_date_time;
    LocalDate end_date_time;
    Boolean paid;
    Double amount;
    LocalDate booking_date;
    User person_who_booked;
}
