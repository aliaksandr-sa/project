package telran.java29.project.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Car;
import telran.java29.project.dto.SearchResultDto;

public interface CarRepository extends MongoRepository<Car, String> {

	Iterable<SearchResultDto> findByLocationNear(Point point, Distance distance, Pageable pageable);
	
}
