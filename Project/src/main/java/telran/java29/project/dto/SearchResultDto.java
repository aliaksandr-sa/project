package telran.java29.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//S
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SearchResultDto {
	 Integer current_page;
	 Integer items_on_page;
	 Integer items_total;
	 CarDto[] cars;

}
