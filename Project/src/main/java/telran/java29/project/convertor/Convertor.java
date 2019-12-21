package telran.java29.project.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import telran.java29.project.domain.BookedCar;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.domain.Comment;
import telran.java29.project.domain.PickUpPlace;
import telran.java29.project.domain.User;
import telran.java29.project.dto.BookedCarDto;
import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.BookedPeriodDtoSimple;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CarDtoSimple;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.OwnCarDto;
import telran.java29.project.dto.OwnerDto;
import telran.java29.project.dto.PickUpPlaceDto;
import telran.java29.project.dto.SearchResultDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.dto.UserWhoBookedDto;
//S
@Component
public class Convertor {
	public CarDto convertToCarDto(Car car) {
		return CarDto.builder().serial_number(car.getSerial_number())
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
				.features(car.getFeatures())
				.car_class(car.getCar_class())
				.price_per_day(car.getPrice_per_day())
				.distance_included(car.getDistance_included())
				.about(car.getAbout())
				.pick_up_place(convertToPickUpPlaceDto(car.getPlace()))
				.image_url(car.getImage_url())
				.owner(convertToOwnerDto(car.getOwner()))
				.booked_periods(car.getBooked_periods().stream().map(this::convertToBookedPeriodDto).collect(Collectors.toSet()))
				.build();
	}

	public BookedPeriodDto convertToBookedPeriodDto(BookedPeriod booked_period) {
		return BookedPeriodDto.builder().order_id(booked_period.getOrder_id())
				.start_date_time(booked_period.getStart_date_time()).end_date_time(booked_period.getEnd_date_time())
				.paid(booked_period.getPaid()).amount(booked_period.getAmount())
				.booking_date(booked_period.getBooking_date())
				.person_who_booked(convertToUserWhoBookedDto(booked_period.getPerson_who_booked())).build();
	}

	public UserWhoBookedDto convertToUserWhoBookedDto(User person_who_booked) {
		return UserWhoBookedDto.builder().email(person_who_booked.getEmail())
				.first_name(person_who_booked.getFirst_name()).second_name(person_who_booked.getSecond_name())
				.phone(person_who_booked.getPhone()).build();
	}

	public OwnerDto convertToOwnerDto(User owner) {
		return OwnerDto.builder()
				.first_name(owner.getFirst_name())
				.second_name(owner.getSecond_name())
				.registration_date(owner.getRegistration_date())
				.build();
	}

//	public PickUpPlaceDto convertToPickUpPlaceDto(PickUpPlace pick_up_place) {
//		return null;
//		return PickUpPlaceDto.builder().place_id(pick_up_place.getPlace_id()).latitude(pick_up_place.getLocation().getLatitude())
//				.longitude(pick_up_place.getLocation().getLatitude()).build();
//	}

//	public PickUpPlace convertToPickUpPlace(PickUpPlaceDto pick_up_place) {
//		
//		return Pick
//	}

	public UserDto convertToUserDto(User user) {
		return UserDto.builder().first_name(user.getFirst_name()).second_name(user.getSecond_name())
				.registration_date(user.getRegistration_date())
				.history(user.getHistory().stream().map(this::convertToBookedCarDto).collect(Collectors.toSet()))
				.booked_cars(
						user.getBooked_cars().stream().map(this::convertToBookedCarDto).collect(Collectors.toSet()))
				.own_cars(user.getOwn_cars().stream().map(this::convertToOwnCarDto).collect(Collectors.toSet()))
				.comments(user.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toSet()))
				.build();
	}

	public BookedCarDto convertToBookedCarDto(BookedCar bookedCar) {
		return new BookedCarDto(bookedCar.getSerial_number(), bookedCar.getBookes_period());
	}

	public OwnCarDto convertToOwnCarDto(Car car) {
		return OwnCarDto.builder().serial_number(car.getSerial_number()).make(car.getMake()).model(car.getModel())
				.year(car.getYear()).engine(car.getEngine()).fuel(car.getFuel()).gear(car.getGear())
				.wheels_drive(car.getWheels_drive()).doors(car.getDoors()).seats(car.getSeats())
				.fuel_consumption(car.getFuel_consumption()).features(car.getFeatures()).car_class(car.getCar_class())
				.price_per_day(car.getPrice_per_day()).distance_included(car.getDistance_included())
				.pick_up_place(convertToPickUpPlaceDto(car.getPlace())).image_url(car.getImage_url())
				.booked_periods(car.getBooked_periods().stream().map(this::convertToBookedPeriodDto)
						.collect(Collectors.toSet()))
				.build();

	}

	public PickUpPlaceDto convertToPickUpPlaceDto(PickUpPlace pickUpPlace) {
		return PickUpPlaceDto.builder().place_id(pickUpPlace.getPlace_id()).latitude(pickUpPlace.getLocation().getY())
				.longitude(pickUpPlace.getLocation().getX()).build();
	}

	public CommentDto convertToCommentDto(Comment comment) {
		return CommentDto.builder()
				.first_name(comment.getFirst_name())
				.second_name(comment.getSecond_name())
				.post(comment.getPost())
				.post_date(comment.getPostdate().toLocalDate())
				.build();
	}
	public PickUpPlace convertToPickUpPlace(PickUpPlaceDto pick_up_place) {
		return new PickUpPlace(pick_up_place.getPlace_id(), new GeoJsonPoint(pick_up_place.getLongitude(), pick_up_place.getLatitude()));
	}

	public User convertToUser(UserWhoBookedDto person_who_booked) {
		return null;
	}
	public CarDtoSimple convertToCarDtoSimple(Car car) {
		return CarDtoSimple.builder().serial_number(car.getSerial_number())
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
				.features(car.getFeatures())
				.car_class(car.getCar_class())
				.price_per_day(car.getPrice_per_day())
				.distance_included(car.getDistance_included())
				.about(car.getAbout())
				.pick_up_place(convertToPickUpPlaceDto(car.getPlace()))
				.image_url(car.getImage_url())
				.owner(convertToOwnerDto(car.getOwner()))
				.booked_periods(car.getBooked_periods().stream().map(this::convertToSimpleBookedPeriodDto).collect(Collectors.toSet()))
				.build();
	}
	BookedPeriodDtoSimple convertToSimpleBookedPeriodDto(BookedPeriod bookedPeriod) {
		return BookedPeriodDtoSimple.builder()
		.start_date_time(bookedPeriod.getStart_date_time())
		.end_date_time(bookedPeriod.getEnd_date_time())
		.build();
		
	}

	public SearchResultDto convertToSearchResultDto(List<CarDto> cars, Pageable paging) {
		return SearchResultDto.builder()
				.cars(cars)
				.current_page(paging.getPageNumber())
				.items_on_page(paging.getPageSize())
				.items_total(cars.size())
				.build();
	}
}
