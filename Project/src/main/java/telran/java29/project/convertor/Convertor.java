package telran.java29.project.convertor;

import java.util.stream.Collectors;

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
				.pick_up_place(convertToPickupPlaceDto(car.getPick_up_place()))
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

	public PickUpPlaceDto convertToPickupPlaceDto(PickUpPlace pick_up_place) {
		return PickUpPlaceDto.builder().place_id(pick_up_place.getPlace_id()).latitude(pick_up_place.getLatitude())
				.longitude(pick_up_place.getLatitude()).build();
	}

	public PickUpPlace convertToPickUpPlace(PickUpPlaceDto pick_up_place) {
		return new PickUpPlace(pick_up_place.getPlace_id(), pick_up_place.getLatitude(), pick_up_place.getLongitude());
	}

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
				.pick_up_place(convertToPickUpPlaceDto(car.getPick_up_place())).image_url(car.getImage_url())
				.booked_periods(car.getBooked_periods().stream().map(this::convertToBookedPeriodDto)
						.collect(Collectors.toSet()))
				.build();

	}

	public PickUpPlaceDto convertToPickUpPlaceDto(PickUpPlace pickUpPlace) {
		return PickUpPlaceDto.builder().place_id(pickUpPlace.getPlace_id()).latitude(pickUpPlace.getLatitude())
				.longitude(pickUpPlace.getLongitude()).build();
	}

	public CommentDto convertToCommentDto(Comment comment) {
		return CommentDto.builder()
				.first_name(comment.getFirst_name())
				.second_name(comment.getSecond_name())
				.post(comment.getPost())
				.post_date(comment.getPost_date())
				.build();
	}
	public PickUpPlace convertToPickupPlace(PickUpPlaceDto pick_up_place) {
		return PickUpPlace.builder()
				.place_id(pick_up_place.getPlace_id())
				.latitude(pick_up_place.getLatitude())
				.longitude(pick_up_place.getLongitude())
				.build();
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
				.pick_up_place(convertToPickupPlaceDto(car.getPick_up_place()))
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
}
