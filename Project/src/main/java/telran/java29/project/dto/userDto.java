package telran.java29.project.dto;

import java.time.LocalDate;
import java.util.Set;

public class userDto {
	String first_name;
	 String second_name;
	  LocalDate registration_date;
	Set<Comment> comments;
	 Set<OwnCar> own_cars;
	  Set<BookedCar> booked_cars;
	 Set<History> history;

}
