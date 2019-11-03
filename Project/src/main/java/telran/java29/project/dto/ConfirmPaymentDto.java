package telran.java29.project.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ConfirmPaymentDto {
	String order_number;
	String confirmation_code;

}
