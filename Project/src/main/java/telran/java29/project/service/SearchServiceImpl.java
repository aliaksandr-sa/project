package telran.java29.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.SearchResultDto;

@Service
//S
public class SearchServiceImpl implements SearchService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	Convertor convertor;
	
	@Override
	public SearchResultDto searchCarsByCoordinates(Double latitude, Double longitude, Double radius, int items_on_page,
			int current_page) {
		Point point = new Point(latitude, longitude);
		Distance distance = new Distance(radius);
		Pageable paging = PageRequest.of(current_page, items_on_page);
		List<CarDto> cars = carRepository.findByPlaceLocationNear(point, distance, paging)
				.stream()
				.map(c->convertor.convertToCarDto(c))
				.collect(Collectors.toList());
		return convertor.convertToSearchResultDto(cars, paging);
	}

	@Override
	public SearchResultDto searchCars(String city, LocalDateTime start_date, LocalDateTime end_date, Double min_amount,
			Double max_amount, boolean ascending, int items_on_page, int current_page) {
		// TODO Auto-generated method stub
		return null;
	}


}
