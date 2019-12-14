package telran.java29.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import telran.java29.project.convertor.Convertor;
import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.Car;
import telran.java29.project.domain.User;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CarDtoSimple;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.exceptions.BadRequestException;
import telran.java29.project.exceptions.ConflictException;

@Service
//m
public class CarServiceImpl implements CarService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	Convertor convertor;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public CarDto addCar(NewCarDto carDto, String email) {
		if (carRepository.existsById(carDto.getSerial_number())) {
			throw new ConflictException();
		}
		User user = userRepository.findById(email).get();
		Car car = new Car(carDto.getSerial_number(), carDto.getMake(), carDto.getModel(), carDto.getYear(),
				carDto.getEngine(), carDto.getFuel(), carDto.getGear(), carDto.getWheels_drive(), carDto.getDoors(),
				carDto.getSeats(), carDto.getFuel_consumption(), carDto.getFeatures(), carDto.getCar_class(),
				carDto.getPrice_per_day(), carDto.getDistance_included(), carDto.getAbout(),
				convertor.convertToPickUpPlace(carDto.getPick_up_place()), carDto.getImage_url());
		car.setOwner(user);
		car = carRepository.save(car);
		user = userRepository.findById(email).get();
		user.addOwnCar(car);
		userRepository.save(user);
		return convertor.convertToCarDto(car);
	}

	@Override
	public CarDto updateCar(NewCarDto updateCar, String serial_number, String email) {
		Car car = new Car();
		try {
			car = carRepository.findById(serial_number).get();
		} catch (Exception e) {
			throw new BadRequestException();
		}
			
			if (!car.getOwner().getEmail().equals(email)) {
				throw new ConflictException();
			}
			if (updateCar.getSerial_number() != null) {
				if (car.getSerial_number() == updateCar.getSerial_number()
						|| carRepository.existsById(updateCar.getSerial_number())) {
					throw new ConflictException();
				}
				Car updatedCar = car;
				carRepository.delete(car);
				updatedCar.setSerial_number(updateCar.getSerial_number());
				updatedCar = updateCar(updatedCar, updateCar);
				carRepository.save(updatedCar);
				return convertor.convertToCarDto(updatedCar);
			} else {
				car = updateCar(car, updateCar);
				carRepository.save(car);
				return convertor.convertToCarDto(car);
			}
	
	}

	private Car updateCar(Car car, NewCarDto updateCar) {
		if (updateCar.getMake() != null) {
			car.setMake(updateCar.getMake());
		}
		if (updateCar.getModel() != null) {
			car.setModel(updateCar.getModel());
		}
		if (updateCar.getYear() <= 0) {
			car.setYear(updateCar.getYear());
		} else if (updateCar.getYear() <= 0) {
			throw new ConflictException();
		}
		if (updateCar.getEngine() != null) {
			car.setEngine(updateCar.getEngine());
		}
		if (updateCar.getFuel() != null) {
			car.setFuel(updateCar.getFuel());
		}
		if (updateCar.getWheels_drive() != null) {
			car.setWheels_drive(updateCar.getWheels_drive());
		}
		if (updateCar.getDoors() <= 0) {
			car.setDoors(updateCar.getDoors());
		} else if (updateCar.getDoors() <= 0) {
			throw new ConflictException();
		}
		if (updateCar.getSeats() <= 0) {
			car.setSeats(updateCar.getSeats());
		} else if (updateCar.getSeats() <= 0) {
			throw new ConflictException();
		}
		if (updateCar.getFuel_consumption() != null) {
			car.setFuel_consumption(updateCar.getFuel_consumption());
		}
		if (updateCar.getFeatures() != null) {
			car.setFeatures(updateCar.getFeatures());
		}
		if (updateCar.getCar_class() != null) {
			car.setCar_class(updateCar.getCar_class());
		}
		if (updateCar.getPrice_per_day() != null) {
			car.setPrice_per_day(updateCar.getPrice_per_day());
		}
		if (updateCar.getDistance_included() != null) {
			car.setDistance_included(updateCar.getDistance_included());
		}
		if (updateCar.getPick_up_place() != null) {
			car.setPlace(convertor.convertToPickUpPlace(updateCar.getPick_up_place()));
		}
		if (updateCar.getAbout() != null) {
			car.setAbout(updateCar.getAbout());
		}
		if (updateCar.getImage_url() != null) {
			car.setImage_url(updateCar.getImage_url());
		}
		return car;
	}

	@Override
	public void deleteCar(String serial_number, String email) {
		Car car = new Car();
		try {
			car = carRepository.findById(serial_number).get();
		} catch (Exception e) {
			throw new BadRequestException();
		}
		if (!car.getOwner().getEmail().equals(email)) {
			throw new ConflictException();
		} else {
			carRepository.delete(car);
		}
	}

	@Override
	public Iterable<CarDtoSimple> get3BestBookedCars() {
		TypedAggregation<Car> filtersAggregation = Aggregation.newAggregation(Car.class, 
				Aggregation.match(Criteria.where("counterBooked").gt(0)),
				Aggregation.sort(Sort.by(Sort.Direction.DESC, "counterBooked")),
				Aggregation.limit(3),
				Aggregation.out("BestCars"));
		mongoTemplate.aggregate(filtersAggregation, Car.class);
		List<Car> cars = mongoTemplate.findAll(Car.class,"BestCars");
		return cars.stream().map(x->convertor.convertToCarDtoSimple(x)).collect(Collectors.toList());
		
		//FIXME convertor PROBLEMS!!!!
		
		

		
		
		
		
		
//										
//		List<Car> cars = carRepository.findAll();
////		Collections.sort(cars, new Comparator<Car>() {
////			public int compare(Car c1, Car c2) {
////				return c2.getBooked_periods().size() - c1.getBooked_periods().size();
////			}
////		});
//		Comparator<Car> reverseSortedByOrders = (car1, car2) -> car2.getCounterBooked() - car1.getCounterBooked();
//		return cars.stream().filter(x -> x.getCounterBooked() >= 1).sorted(reverseSortedByOrders)
//				.filter(x -> cars.indexOf(x) <= 2).map(x -> convertor.convertToCarDtoSimple(x))
//				.collect(Collectors.toList());
	}
}
