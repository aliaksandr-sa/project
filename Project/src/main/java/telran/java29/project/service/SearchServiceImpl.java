package telran.java29.project.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import telran.java29.project.dto.SearchResultDto;

@Service
//S
public class SearchServiceImpl implements SearchService {

	@Override
	public Iterable<SearchResultDto> searchCar(String city, LocalDateTime start_date, LocalDateTime end_date, Double min_amount,
			Double max_amount, boolean ascending, int items_on_page, int current_page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<SearchResultDto> searchByCoordinates(Double latitude, Double longitude, Double radius, int items_on_page,
			int current_page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<SearchResultDto> searchByFilters(String make, String model, int year, String engine, String fuel, String gear,
			int wheels_drive, int items_on_page, int current_page) {
		// TODO Auto-generated method stub
		return null;
	}

}
