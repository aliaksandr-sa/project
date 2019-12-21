package telran.java29.project.dto.filters;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java29.project.dto.CarDtoSimple;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//m
public class SearchByFiltersDto {
	List<CarDtoSimple> cars;
	Iterable<FilterDto> filter;
}
