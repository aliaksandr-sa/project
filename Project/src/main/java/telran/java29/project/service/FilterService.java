package telran.java29.project.service;

import telran.java29.project.dto.filters.FilterDto;
import telran.java29.project.dto.filters.SearchByFiltersDto;

//m
public interface FilterService {

	Iterable<FilterDto> getFilters();
	SearchByFiltersDto searchByFilters(String make, String model, String year, String engine, String fuel, String gear, String wheels_drive);
}
