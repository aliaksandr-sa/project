package telran.java29.project.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter

@EqualsAndHashCode(of = {"serial_number"})
//m
public class Car {
	@Id
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
    String about;
    PickUpPlace pick_up_place;
    Set<String> image_url;
    @Setter
    User owner;
    @Setter
    Set<BookedPeriod> booked_periods;
    
	public Car(String serial_number, String make, String model, int year, String engine, String fuel, String gear,
			String wheels_drive, int doors, int seats, Double fuel_consumption, Set<String> features, String car_class,
			Double price_per_day, Double distance_included, String about, PickUpPlace pick_up_place,
			Set<String> image_url) {
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
		this.about = about;
		this.pick_up_place = pick_up_place;
		this.image_url = new HashSet<>();
		this.owner = null;
		this.booked_periods = new HashSet<>();
	}
}
