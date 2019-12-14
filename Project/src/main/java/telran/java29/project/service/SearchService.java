package telran.java29.project.service;

import telran.java29.project.dto.SearchResultDto;
//S
public interface SearchService {

	SearchResultDto searchCarsByCoordinates(Double latitude, Double longitude, Double radius);

}
