package telran.java29.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BookedCarDto {
	String serial_number;
    BookedPeriodDto bookes_period;
}
