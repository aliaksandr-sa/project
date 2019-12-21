package telran.java29.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "email")
public class PersonWhoBooked {
	String email;
    String first_name;
    String second_name;
    String phone;

}
