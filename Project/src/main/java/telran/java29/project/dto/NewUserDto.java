package telran.java29.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
//S
public class NewUserDto {
	String email;
	String password;
	String first_name;
	String second_name;
}
