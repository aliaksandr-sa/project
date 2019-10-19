package telran.java29.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LatestCommentsDto {
	String first_name;
	String second_name;
	LocalDate post_date;
	String post;

}
