package telran.java29.project.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java29.project.domain.Car;

public interface CarRepository extends MongoRepository<Car, String> {

	List<Car> findByPlaceLocationNear(Point point, Distance distance, Pageable paging);

	
}
