package telran.java29.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
//S
public class OwnerDto {
	 String first_name;
	 String second_name;
	 LocalDate registration_date;
}
