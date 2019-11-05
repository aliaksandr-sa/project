package telran.java29.project.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDate start_date_time;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDate end_date_time;
    Boolean paid;
    Double amount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDate booking_date;
    User person_who_booked;
}
