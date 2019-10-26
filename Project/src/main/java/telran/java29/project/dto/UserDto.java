package telran.java29.project.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//S
public class UserDto {
	String first_name;
	String second_name;
	LocalDate registration_date;
	Set<CommentDto> comments;
	Set<OwnCarDto> own_cars;
	Set<BookedCarDto> booked_cars;
	Set<BookedCarDto> history;
	
}
