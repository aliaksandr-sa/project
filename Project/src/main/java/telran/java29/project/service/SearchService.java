package telran.java29.project.service;

import telran.java29.project.dto.SearchResultDto;

//S
public interface SearchService {

	SearchResultDto searchCarsByCoordinates(Double latitude, Double longitude, Double radius, int items_on_page,
			int current_page);

	SearchResultDto searchCars(String city, String start_date, String end_date, String min_amount,
			String max_amount, boolean ascending, int items_on_page, int current_page);

}
