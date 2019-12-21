package telran.java29.project.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CarDtoSimple;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.NewCommentDto;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.OwnCarDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.dto.SearchResultDto;
import telran.java29.project.dto.UpdateUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.dto.filters.FilterDto;
import telran.java29.project.dto.filters.SearchByFiltersDto;
import telran.java29.project.service.CarService;
import telran.java29.project.service.CommentService;
import telran.java29.project.service.FilterService;
import telran.java29.project.service.FindService;
import telran.java29.project.service.ReservationService;
import telran.java29.project.service.SearchService;
import telran.java29.project.service.UserService;

@RestController
//S
public class Controller {
	@Autowired
	UserService userService;
	@Autowired
	CarService carService;
	@Autowired
	FindService findService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	CommentService commentService;
	@Autowired
	SearchService searchService;
	@Autowired
	FilterService filterService;

	@PostMapping("/registration")
	public UserDto RegisterNewUser(@RequestBody NewUserDto newUser, @RequestHeader("Authorization") String token) {
		return userService.addNewUser(newUser, token);
	}

	@GetMapping("/user/login")
	public UserDto LoginUser(Authentication authentication) {
		return userService.userLogin(authentication.getName());
	}

	@PutMapping("/user")
	public UserDto UpdateUser(@RequestBody UpdateUserDto updateUser, Authentication authentication,
			@RequestHeader("X-Password") String password) {
		return userService.userUpdate(updateUser, authentication.getName(), password);
	}

	@DeleteMapping("/user")
	public void DeleteUser(Authentication authentication) {
		userService.userDelete(authentication.getName());
	}

	@PostMapping("/car")
	public CarDto addCar(@RequestBody NewCarDto newCar, Authentication authentication) {
		return carService.addCar(newCar, authentication.getName());
	}

	@PutMapping("/car")
	public CarDto updateCar(@RequestBody NewCarDto updateCar, @RequestParam(value = "number") String serial_number,
			Authentication authentication) {
		return carService.updateCar(updateCar, serial_number, authentication.getName());
	}

	@DeleteMapping("/car")
	public void deleteCar(@RequestParam(value = "number") String serial_number, Authentication authentication) {
		carService.deleteCar(serial_number, authentication.getName());
	}

	@GetMapping("/car")
	public CarDto getCarById(@RequestParam(value = "number") String serial_number) {
		return findService.getCarById(serial_number);
		// TODO in implementation not to give full view of bookedPeriodDto!
	}

	@GetMapping("/user/cars")
	public Iterable<OwnCarDto> ownerGetCars(Authentication authentication) {
		return findService.ownerGetCars(authentication.getName());
	}

	@GetMapping("/user/cars/number")
	public OwnCarDto ownerGetCarById(@RequestParam(value = "number") String serial_number,
			Authentication authentication) {
		return findService.ownerGetCarById(serial_number, authentication.getName());
	}

	@GetMapping("/user/cars/periods")
	public Iterable<BookedPeriodDto> ownerGetBookedPeriodsByCarId(@RequestParam(value = "number") String serial_number,
			Authentication authentication) {
		return findService.ownerGetBookedPeriodsByCarId(serial_number, authentication.getName());
	}

	@GetMapping("/search")
	public SearchResultDto searchCar(@RequestParam(required = false) String city,
			@RequestParam(required = false) LocalDateTime start_date,
			@RequestParam(required = false) LocalDateTime end_date, @RequestParam(required = false) Double min_amount,
			@RequestParam(required = false) Double max_amount, @RequestParam(required = false) boolean ascending,
			@RequestParam int items_on_page, @RequestParam int current_page) {
		return searchService.searchCars(city, start_date, end_date, min_amount, max_amount, ascending, items_on_page, current_page);
	}

	@GetMapping("/search/geo")
	public SearchResultDto searchCarsByCoordinates(@RequestParam Double latitude, @RequestParam Double longitude,
			@RequestParam Double radius, @RequestParam int items_on_page, @RequestParam int current_page) {
		return searchService.searchCarsByCoordinates(latitude, longitude, radius, items_on_page, current_page);

	}

	@GetMapping("/search/filters")
	public SearchByFiltersDto SearchByFilters(@RequestParam(required = false) String make,
			@RequestParam(required = false) String model, @RequestParam(required = false) String year,
			@RequestParam(required = false) String engine, @RequestParam(required = false) String fuel,
			@RequestParam(required = false) String gear, @RequestParam(required = false) String wheels_drive,
			@RequestParam int items_on_page, @RequestParam int current_page) {
		return filterService.searchByFilters(make, model, year, engine, fuel, gear, wheels_drive, items_on_page,
				current_page);
	}

	@PostMapping("/car/reservation/{serial_number}")
	public ReservationResponseDto makeAReservation(@RequestBody ReservationDto reservationDto,
			@PathVariable String serial_number) {
		return reservationService.makeAReservation(reservationDto, serial_number);
	}

	@PostMapping("/reservation/confirm")
	public void makeAReservation(@RequestBody ConfirmPaymentDto confirmPaymentDto) {
		reservationService.makeAReservation(confirmPaymentDto);
	}

	@GetMapping("/car/best")
	public Iterable<CarDtoSimple> get3BestBookedCars() {
		return carService.get3BestBookedCars();
	}

	@GetMapping("/comments")
	public Iterable<CommentDto> getLatestComments() {
		return commentService.getLatestComments();
	}

	@PostMapping("/comment")
	public void AddAComment(@RequestParam(value = "number") String serial_number, @RequestBody NewCommentDto post,
			Authentication authentication) {
		commentService.addAComment(serial_number, post, authentication.getName());
	}

	@GetMapping("/filters")
	public Iterable<FilterDto> getFilters() {
		return filterService.getFilters();
	}
}
