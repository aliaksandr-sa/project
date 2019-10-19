package telran.java29.project.domain;

import java.util.Set;

import telran.java29.project.dto.BookedPeriodDto;
import telran.java29.project.dto.OwnerDto;
import telran.java29.project.dto.PickUpPlaceDto;

public class Car {
	String serial_number;
    String make;
    String model;
    int year;
    String engine;
    String fuel;
    String gear;
    String wheels_drive;
    int doors;
    int seats;
    Double fuel_consumption; 
    Set<String> features;
    String car_class;
    Double price_per_day;
    Double distance_included;
    PickUpPlaceDto pick_up_place;
    Set<String> image_url;
    OwnerDto owner;
    Set<BookedPeriodDto> booked_periods;
}
