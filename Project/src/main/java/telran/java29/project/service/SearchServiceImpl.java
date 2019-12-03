package telran.java29.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dto.SearchResultDto;

@Service
//S
public class SearchServiceImpl implements SearchService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	Convertor convertor;
	
	@Override
	public Iterable<SearchResultDto> searchCarsByCoordinates(Double latitude, Double longitude, Double radius,
			int items_on_page, int current_page) {
		Pageable pageable = PageRequest.of(current_page, items_on_page);
		return carRepository.findByLocationNear(new Point(latitude, longitude), new Distance(radius), pageable);
	}


}
