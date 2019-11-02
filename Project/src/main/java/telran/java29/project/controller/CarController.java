package telran.java29.project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java29.project.domain.BookedPeriod;
import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.CarDto;
import telran.java29.project.dto.CommentDto;
import telran.java29.project.dto.ConfirmPaymentDto;
import telran.java29.project.dto.NewCarDto;
import telran.java29.project.dto.NewCommentDto;
import telran.java29.project.dto.ReservationDto;
import telran.java29.project.dto.ReservationResponseDto;
import telran.java29.project.dto.UpdateCarDto;
import telran.java29.project.service.CarService;
import telran.java29.project.service.FindService;

@RestController
//m
public class CarController {
	@Autowired
	CarService carService;
	@Autowired
	FindService findService;

	@PostMapping("/car")
	public CarDto addCar(@RequestBody NewCarDto newCar) {
		return carService.addCar(newCar);
	}

	@PutMapping("/car/{serial_number}")
	public CarDto updateCar(@RequestBody UpdateCarDto updateCar, @PathVariable String serial_number) {
		return carService.updateCar(updateCar, serial_number);
	}

	@DeleteMapping("/car/{serial_number}")
	public void deleteCar(String serial_number) {
		carService.deleteCar(serial_number);
	}

	@GetMapping("/car/{serial_number}")
	public CarDto getCarById(@PathVariable String serial_number) {
		return findService.getCarById(serial_number);
		// TODO in implementation not to give full view of bookedPeriodDto!
	}

	@GetMapping("/user/cars/{serial_number}")
	public CarDto ownerGetCarById(@PathVariable String serial_number) {
		return findService.findOwnerCarById(serial_number);
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
	public ReservationResponseDto makeAReservation(ReservationDto reservationDto) {
		return carService.makeAReservation(reservationDto);
	}
	
	@PostMapping("/reservation/confirm")
	public void makeAReservation(ConfirmPaymentDto confirmPaymentDto) {
		carService.makeAReservation(confirmPaymentDto);
	}
	
	@GetMapping("/car/best")
	public List<CarDto> get3BestBookedCars(){
		return carService.get3BestBookedCars();
	}
	
	@GetMapping("/comment")
	public Set<CommentDto> getLatestComments(){
		return carService.getLatestComments();
	}
	
	@PostMapping("/comment/{serial_number}")
	public void AddAComment(NewCommentDto newComment) {
		carService.addAComment();
	}
	
	//@GetMapping("/filters")
	//ya ebu cho tut pisat'
	
	
	
	
	
	
	
	
	
}
