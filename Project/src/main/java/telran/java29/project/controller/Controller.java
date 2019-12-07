package telran.java29.project.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
import telran.java29.project.service.CarService;
import telran.java29.project.service.CommentService;
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

	@GetMapping("/user/cars")
	public OwnCarDto ownerGetCarById(@RequestParam(value = "number") String serial_number, Authentication authentication) {
		return findService.ownerGetCarById(serial_number, authentication.getName());
	}

	@GetMapping("/user/cars/periods")
	public Iterable<BookedPeriodDto> ownerGetBookedPeriodsByCarId(@RequestParam(value = "number") String serial_number,
			Authentication authentication) {
		return findService.ownerGetBookedPeriodsByCarId(serial_number, authentication.getName());
	}

//	@GetMapping("/search?country=string&city=string&start_date="YYYY-MM-dd HH:mm"&end_date="YYYY-MM-dd HH:mm"&ascending=true&min_amount=20.5&max_amount=35.5")
//	public List<CarDto> search car by place and start/end dates(???){
//		return carService.searchWithoutFilters(???);
//	}

//	@GetMapping("/search/geo")
//	public Iterable<SearchResultDto> searchCarsByCoordinates(@RequestParam(value = "latitude") Double latitude, @RequestParam Double longitude,
//			@RequestParam Double radius, @RequestParam int items_on_page, @RequestParam int current_page) {
//		return searchService.searchCarsByCoordinates(latitude, longitude, radius, items_on_page, current_page);
//
//	}

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
	@ResponseBody
	public void AddAComment(@RequestParam(value ="number") String serial_number, @RequestBody NewCommentDto post,
			Authentication authentication) {
		commentService.addAComment(serial_number, post, authentication.getName());
	}

	// @GetMapping("/filters")
	// ya ebu cho tut pisat'
}
