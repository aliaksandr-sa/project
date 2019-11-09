package telran.java29.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.NewUserDto;
import telran.java29.project.dto.OwnCarDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.dto.UpdateCarDto;
import telran.java29.project.dto.UpdateUserDto;
import telran.java29.project.dto.UserDto;
import telran.java29.project.service.CarService;
import telran.java29.project.service.CommentService;
import telran.java29.project.service.FindService;
import telran.java29.project.service.ReservationService;
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

	@PostMapping("/registration")
	public UserDto RegisterNewUser(@RequestBody NewUserDto newUser) {
		return userService.addNewUser(newUser);
	}

	@GetMapping("/user/{login}")
	public UserDto LoginUser(@PathVariable String login) {
		return userService.userLogin(login);
	}

	@PutMapping("/user/{id}")
	public UserDto UpdateUser(@RequestBody UpdateUserDto updateUser, @PathVariable String id, @RequestHeader("X-New-Password") String password) {
		return userService.userUpdate(updateUser, id, password);
	}

	@DeleteMapping("/user/{id}")
	public void DeleteUser(@PathVariable String id) {
		userService.userDelete(id);
	}
	@PostMapping("/car")
	public CarDto addCar(@RequestBody NewCarDto newCar) {
		return carService.addCar(newCar);
	}

	@PutMapping("/car/{serial_number}")
	public CarDto updateCar(@RequestBody UpdateCarDto updateCar, @PathVariable String serial_number) {
		return carService.updateCar(updateCar, serial_number);
	}

	@DeleteMapping("/car/{serial_number}")
	public void deleteCar(@PathVariable String serial_number) {
		carService.deleteCar(serial_number);
	}

	@GetMapping("/car/{serial_number}")
	public CarDto getCarById(@PathVariable String serial_number) {
		return findService.getCarById(serial_number);
		// TODO in implementation not to give full view of bookedPeriodDto!
	}

	@GetMapping("/user/cars")
	public Iterable<OwnCarDto>ownerGetCars(@PathVariable String id){
		return findService.ownerGetCars(id);
	}
	@GetMapping("/user/cars/{serial_number}")
	public OwnCarDto ownerGetCarById(@PathVariable String serial_number) {
		return findService.ownerGetCarById(serial_number);
	}

	@GetMapping("/user/cars/{serial_number}/periods")
	public Iterable<BookedPeriodDto> ownerGetBookedPeriodsByCarId(@PathVariable String serial_number) {
		return findService.ownerGetBookedPeriodsByCarId(serial_number);
	}
	

//	@GetMapping("/search?country=string&city=string&start_date="YYYY-MM-dd HH:mm"&end_date="YYYY-MM-dd HH:mm"&ascending=true&min_amount=20.5&max_amount=35.5")
//	public List<CarDto> search car by place and start/end dates(???){
//		return carService.searchWithoutFilters(???);
//	}
	
//	@PostMapping("/search?country=string&city=string&start_date="YYYY-MM-dd HH:mm"&end_date="YYYY-MM-dd HH:mm"&ascending=true&min_amount=20.5&max_amount=35.5")
//	public List<CarDto> search car by place and start/end dates(???){
//		return carService.searctWithFilters(???);
	
	@PostMapping("/car/{serial_number}/reservation")
	public ReservationResponseDto makeAReservation(@RequestBody ReservationDto reservationDto, @PathVariable String serial_number) {
		return reservationService.makeAReservation(reservationDto, serial_number);
	}
	
	@PostMapping("/reservation/confirm")
	public void makeAReservation(@RequestBody ConfirmPaymentDto confirmPaymentDto) {
		reservationService.makeAReservation(confirmPaymentDto);
	}
	
	@GetMapping("/car/best")
	public Iterable<CarDto> get3BestBookedCars(){
		return carService.get3BestBookedCars();
	}
	
	@GetMapping("/comment")
	public Iterable<CommentDto> getLatestComments(){
		return commentService.getLatestComments();
	}
	
	@PostMapping("/comment/{serial_number}")
	public void AddAComment(@PathVariable String serial_number, @RequestBody String post) {
		commentService.addAComment(serial_number, post);
	}
	
	//@GetMapping("/filters")
	//ya ebu cho tut pisat'
}
