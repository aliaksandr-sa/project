package telran.java29.project.service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.domain.Car;
import telran.java29.project.dto.CarDtoSimple;
import telran.java29.project.dto.SearchResultDto;

@Service
//S
public class SearchServiceImpl implements SearchService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	Convertor convertor;
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public SearchResultDto searchCarsByCoordinates(Double latitude, Double longitude, Double radius, int items_on_page,
			int current_page) {
		Point point = new Point(latitude, longitude);
		Distance distance = new Distance(radius);
		Pageable paging = PageRequest.of(current_page, items_on_page);
		List<CarDtoSimple> cars = carRepository.findByPlaceLocationNear(point, distance, paging)
				.stream()
				.map(c->convertor.convertToCarDtoSimple(c))
				.collect(Collectors.toList());
		return convertor.convertToSearchResultDto(cars, paging);
	}

	@Override
	public SearchResultDto searchCars(String city, String start_date, String end_date, String min_amount,
			String max_amount, boolean ascending, int items_on_page, int current_page) {
		if (min_amount!=null && max_amount==null) {
			max_amount = "999999999999999.9";
		}
		if (max_amount!=null && min_amount == null) {
			min_amount = "0.0";
		}
		Pageable paging = PageRequest.of(current_page, items_on_page);
		//Search cars**********************************
		Map<String, String> filter = new LinkedHashMap<String, String>();
		filter.put(city, "place.city");
		filter.put(start_date, "booked_periods.start_date_time");
		filter.put(end_date, "booked_periods.end_date_time");
		filter.put( min_amount, "price_per_day");
		filter.put( max_amount, "price_per_day");
////		filter.put("fuel", fuel);
////		filter.put("gear", gear);
////		filter.put("wheels_drive", wheels_drive);
//		LocalDateTime startTime = LocalDateTime.parse(start_date);
		Query query = new Query();
		// dobavlyau iz mapi v criteria, proverayu na null znacheniya
		for (Map.Entry<String, String> entry : filter.entrySet()) {
			if (entry.getKey() != null) {
				if (entry.getKey().equals(start_date)) {
					query.addCriteria(Criteria.where(entry.getValue()).gte(LocalDateTime.parse(entry.getKey())));
				}else if (entry.getKey().equals(end_date)) {
					query.addCriteria(Criteria.where(entry.getValue()).lte(LocalDateTime.parse(entry.getKey())));
				}else if (entry.getKey().equals(min_amount) || entry.getKey().equals(max_amount)) {
					query.addCriteria(Criteria.where(entry.getValue()).in(Double.parseDouble(min_amount), Double.parseDouble(max_amount)));
				}else {
					query.addCriteria(Criteria.where(entry.getValue()).is(entry.getKey()));
				}
				
			}
		}
		
//		query.addCriteria(Criteria.where("booked_periods.start_date_time").gte(startTime));
//		query.addCriteria(Criteria.where("place.city").is(city));
		List<Car> cars = mongoTemplate.find(query, Car.class, "cars");
		List<CarDtoSimple> simpleCars = cars.stream().map(c-> convertor.convertToCarDtoSimple(c)).collect(Collectors.toList());
		
		return convertor.convertToSearchResultDto(simpleCars, paging);
	}
//	@Override
//	public SearchResultDto searchCars(String city, String start_date, String end_date, Double min_amount,
//			Double max_amount, boolean ascending, int items_on_page, int current_page) {
//		
//		Pageable paging = PageRequest.of(current_page, items_on_page);
//		
//		Query query = new Query();
//				query.addCriteria(Criteria.where("place.city").is(city));
//				
//					query.addCriteria(Criteria.where("booked_periods.start_date_time").gte(LocalDateTime.of(2019, 12, 21, 10, 02)));
//					System.out.println(LocalDateTime.parse(start_date));
//					System.out.println(LocalDateTime.of(2019, 12, 22, 10, 02));
//				
//				
//				query.with(paging);
//			
//		
//		List<Car> cars = mongoTemplate.find(query, Car.class, "cars");
//		List<CarDtoSimple> simpleCars = cars.stream().map(c-> convertor.convertToCarDtoSimple(c)).collect(Collectors.toList());
//		
//		return convertor.convertToSearchResultDto(simpleCars, paging);
//	}


}
