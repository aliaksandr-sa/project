package telran.java29.project.dto;

import java.time.LocalDate;
import java.util.Set;

public class UserDto {
	String first_name;
	 String second_name;
	  LocalDate registration_date;
	Set<CommentDto> comments;
	 Set<CarDto> own_cars;
	  Set<BookedCarDto> booked_cars;
	 Set<HistoryDto> history;

}
