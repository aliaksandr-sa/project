package telran.java29.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java29.project.dao.CarRepository;
import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.domain.PickUpPlace;
import telran.java29.project.domain.User;
import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.OwnerDto;
import telran.java29.project.dto.PickUpPlaceDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.dto.UpdateCarDto;
import telran.java29.project.dto.UserWhoBookedDto;
@Service
public class CarServiceImpl implements CarService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository carRepository;

	@Override
	public CarDto addCar(NewCarDto carDto) {
		Car car = new Car(carDto.getSerial_number(), carDto.getMake(), carDto.getModel(), 
				carDto.getYear(), carDto.getEngine(), carDto.getFuel(), carDto.getGear(), 
				carDto.getWheels_drive(), carDto.getDoors(), carDto.getSeats(), 
				carDto.getFuel_consumption(), carDto.getFeatures(), carDto.getCar_class(), 
				carDto.getPrice_per_day(), carDto.getDistance_included(), carDto.getAbout(), 
				convertToPickUpPlace(carDto.getPick_up_place()), carDto.getImage_url());
		car = carRepository.save(car);
		return convertToCarDto(car);
		
	}


	@Override
	public CarDto updateCar(UpdateCarDto updateCar, String serial_number) {
		Car car = carRepository.findById(serial_number).get();
		if(!updateCar.getFeatures().isEmpty()) {
			car.setFeatures(updateCar.getFeatures().stream().collect(Collectors.toSet()));
		}
		if (updateCar.getCar_class()!=null) {
			car.setCar_class(updateCar.getCar_class());
		}
		if (updateCar.getPrice_per_day()!=null) {
			car.setPrice_per_day(updateCar.getPrice_per_day());
		}
		if (updateCar.getDistance_included()!=null) {
			car.setDistance_included(updateCar.getDistance_included());
		}
		if (updateCar.get) {
			
		}
		return null;
	}

	@Override
	public CarDto findCarById(String serial_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCar() {
		// TODO Auto-generated method stub

	}

	@Override
	public CarDto findOwnerCarById(String serial_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookedPeriod> findOwnerBookedPeriodsByCarId(String serial_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationResponseDto makeAReservation(ReservationDto reservationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeAReservation(ConfirmPaymentDto confirmPaymentDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CarDto> get3BestBookedCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CommentDto> getLatestComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAComment() {
		// TODO Auto-generated method stub

	}

	public CarDto convertToCarDto(Car car) {
		return CarDto.builder()
				.serial_number(car.getSerial_number())
				.make(car.getMake())
				.model(car.getModel())
				.year(car.getYear())
				.engine(car.getEngine())
				.fuel(car.getFuel())
				.gear(car.getGear())
				.wheels_drive(car.getWheels_drive())
				.doors(car.getDoors())
				.seats(car.getSeats())
				.fuel_consumption(car.getFuel_consumption())
				.features(car.getFeatures().stream().collect(Collectors.toSet()))
				.car_class(car.getCar_class())
				.price_per_day(car.getPrice_per_day())
				.distance_included(car.getDistance_included())
				.about(car.getAbout())
				.pick_up_place(convertPickUpPlaceToPickupPlaceDto(car.getPick_up_place()))
				.image_url(car.getImage_url().stream().collect(Collectors.toSet()))
				.owner(convertOwnerToOwnerDto(car.getOwner()))
				.booked_periods(car.getBooked_periods().stream().map(this::convertBookedPeriodsToBookedPeriodDto).collect(Collectors.toSet()))
				.build();
	}

	public BookedPeriodDto convertBookedPeriodsToBookedPeriodDto(BookedPeriod booked_period) {
		return BookedPeriodDto.builder()
				.order_id(booked_period.getOrder_id())
				.start_date_time(booked_period.getStart_date_time())
				.end_date_time(booked_period.getEnd_date_time())
				.paid(booked_period.getPaid())
				.amount(booked_period.getAmount())
				.booking_date(booked_period.getBooking_date())
				.person_who_booked(convertUserToUserWhoBookedDto(booked_period.getPerson_who_booked()))
				.build();
	}

	public UserWhoBookedDto convertUserToUserWhoBookedDto(User person_who_booked) {
		return UserWhoBookedDto.builder()
				.email(person_who_booked.getEmail())
				.first_name(person_who_booked.getFirst_name())
				.second_name(person_who_booked.getSecond_name())
				.phone(person_who_booked.getPhone())
				.build();
	}

	public OwnerDto convertOwnerToOwnerDto(User owner) {
		return OwnerDto.builder()
			.first_name(owner.getFirst_name())
			.second_name(owner.getSecond_name())
			.registration_date(owner.getRegistration_date())
			.build();
	}

	public PickUpPlaceDto convertPickUpPlaceToPickupPlaceDto(PickUpPlace pick_up_place) {
		return PickUpPlaceDto.builder()
				.place_id(pick_up_place.getPlace_id())
				.latitude(pick_up_place.getLatitude())
				.longitude(pick_up_place.getLatitude())
				.build();
	}

	public PickUpPlace convertToPickUpPlace(PickUpPlaceDto pick_up_place) {
		return new PickUpPlace(pick_up_place.getPlace_id(), pick_up_place.getLatitude(), pick_up_place.getLongitude());
	}
}
