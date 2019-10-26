package telran.java29.project.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import telran.java29.project.dao.UserRepository;
import telran.java29.project.domain.BookedCar;
import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.domain.Car;
import telran.java29.project.domain.Comment;
import telran.java29.project.domain.PickUpPlace;
import telran.java29.project.domain.User;
import telran.java29.project.dto.BookedCarDto;
import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.OwnCarDto;
import telran.java29.project.dto.PickUpPlaceDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.dto.UserWhoBookedDto;
import telran.java29.project.exceptions.UserConflictException;

public class UserServiseImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDto addNewUser(NewUserDto newUser) {
		if (userRepository.existsById(newUser.getEmail())) {
			throw new UserConflictException();
		}
		String hashPassword = passwordEncoder.encode(newUser.getPassword());
		User user = new User(newUser.getFirst_name(), newUser.getSecond_name(), newUser.getEmail(),
				newUser.getPassword());
		user = userRepository.save(user);
		return convertToUserDto(user);
	}

	

	@Override
	public UserDto userLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto userUpdate(NewUserDto updateUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void userDelete() {
		// TODO Auto-generated method stub

	}
	
	private UserDto convertToUserDto(User user) {
		return UserDto.builder().first_name(user.getFirst_name())
				.second_name(user.getSecond_name())
				.registration_date(user.getRegistration_date())
				.history(user.getHistory().stream().map(this::convertToBookedCarDto).collect(Collectors.toSet()))
				.booked_cars(user.getBooked_cars().stream().map(this::convertToBookedCarDto).collect(Collectors.toSet()))
				.own_cars(user.getOwn_cars().stream().map(this::convertToOwnCarDto).collect(Collectors.toSet()))
				.comments(user.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toSet()))
				.buid();
	}
	private BookedCarDto convertToBookedCarDto(BookedCar bookedCar) {
		return new BookedCarDto(bookedCar.getSerial_number(), bookedCar.getBookes_period());
	}
	private OwnCarDto convertToOwnCarDto(Car car) {
		return OwnCarDto.builder()
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
				.features(car.getFeatures())
				.car_class(car.getCar_class())
				.price_per_day(car.getPrice_per_day())
				.distance_included(car.getDistance_included())
				.pick_up_place(convertToPickUpPlaceDto(car.getPick_up_place()))
				.image_url(car.getImage_url())
				.booked_periods(car.getBooked_periods().stream().map(this::convertToBookedPeriodDto).collect(Collectors.toSet()))
				.build();
				
	}
	private BookedPeriodDto convertToBookedPeriodDto(BookedPeriod bookedPeriod) {
		return BookedPeriodDto.builder()
				.order_id(bookedPeriod.getOrder_id())
				.start_date_time(bookedPeriod.getStart_date_time())
				.end_date_time(bookedPeriod.getEnd_date_time())
				.paid(bookedPeriod.getPaid())
				.amount(bookedPeriod.getAmount())
				.booking_date(bookedPeriod.getBooking_date())
				.person_who_booked(convertToUserWhoBookedDto(bookedPeriod.getPerson_who_booked()))
				.build();
	}


	private UserWhoBookedDto convertToUserWhoBookedDto(User user) {
		return UserWhoBookedDto.builder()
				.email(user.getEmail())
				.first_name(user.getFirst_name())
				.second_name(user.getSecond_name())
				.phone(user.getPhone())
				.build();
	}



	private PickUpPlaceDto convertToPickUpPlaceDto(PickUpPlace pickUpPlace) {
		return PickUpPlaceDto.builder()
				.place_id(pickUpPlace.getPlace_id())
				.latitude(pickUpPlace.getLatitude())
				.longitude(pickUpPlace.getLongitude())
				.build();
	}
	private CommentDto convertToCommentDto(Comment comment) {
		return CommentDto.builder()
				.first_name(comment.getFirst_name())
				.second_name(comment.getSecond_name())
				.post(comment.getPost())
				.post_date(comment.getPost_date())
				.build();
	}

}
