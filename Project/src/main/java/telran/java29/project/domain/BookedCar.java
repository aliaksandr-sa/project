package telran.java29.project.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java29.project.dto.BookedPeriodDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"serial_number", "bookes_period"})
//S
public class BookedCar {
	String serial_number;
    BookedPeriodDto bookes_period;
}
