package telran.java29.project.service;

import java.time.LocalDateTime;

import telran.java29.project.dto.SearchResultDto;
//S
public interface SearchService {
	Iterable<SearchResultDto> searchCar (String city, LocalDateTime start_date, LocalDateTime end_date, Double min_amount,
			Double max_amount, boolean ascending, int items_on_page, int current_page);
	
	Iterable<SearchResultDto> searchByCoordinates (Double latitude, Double longitude, Double radius, 
			int items_on_page, int current_page);
	
	Iterable<SearchResultDto> searchByFilters (String make, String model, int year, String engine, String fuel, 
			String gear, int wheels_drive, int items_on_page, int current_page);
}
