package telran.java29.project.service;

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
	public Iterable<SearchResultDto> searchCarsByCoordinates(Double latitude, Double longitude, Double radius) {
		Point point = new Point(latitude, longitude);
		Distance distance = new Distance(radius);
		List<CarDto> cars = carRepository.findByPickUpPlaceNear(point, distance)
				.stream()
				.map(c->convertor.convertToCarDto(c))
				.collect(Collectors.toList());
		return convertor.convertToSearchResultDto(cars);
	}


}
