package telran.java29.project.dto;

import java.util.List;

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
	int current_page;
	 int items_on_page;
	 int items_total;
	 List<CarDto> cars;

}
