package telran.java29.project.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java29.project.dto.SearchResultDto;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//m
public class SearchByFiltersDto {
	SearchResultDto searchResultDto;
	Iterable<FilterDto> filter;
}
