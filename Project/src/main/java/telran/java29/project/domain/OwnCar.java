package telran.java29.project.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java29.project.dto.PickUpPlaceDto;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"serial_number"})
public class OwnCar {
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
    double fuel_consumption;
    Set<String> features;
    String car_class;
    Double price_per_day;
    Double distance_included;
    PickUpPlaceDto pick_up_place;
    Set<String> image_url;
    Set<BookedPeriod> booked_periods;
	public OwnCar(String serial_number, String make, String model, int year, String engine, String fuel, String gear,
			String wheels_drive, int doors, int seats, double fuel_consumption, Set<String> features, String car_class,
			Double price_per_day, Double distance_included, PickUpPlaceDto pick_up_place, Set<String> image_url) {
		super();
		this.serial_number = serial_number;
		this.make = make;
		this.model = model;
		this.year = year;
		this.engine = engine;
		this.fuel = fuel;
		this.gear = gear;
		this.wheels_drive = wheels_drive;
		this.doors = doors;
		this.seats = seats;
		this.fuel_consumption = fuel_consumption;
		this.features = features;
		this.car_class = car_class;
		this.price_per_day = price_per_day;
		this.distance_included = distance_included;
		this.pick_up_place = pick_up_place;
		this.image_url = image_url;
		booked_periods = new HashSet<>();
	}
    
    
}
