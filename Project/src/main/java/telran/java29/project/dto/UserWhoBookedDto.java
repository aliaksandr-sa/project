package telran.java29.project.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWhoBookedDto {
	String email;
    String first_name;
    String second_name;
    String phone;

}
