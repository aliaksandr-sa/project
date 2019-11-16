package telran.java29.project.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Document(collection = "cars")
@EqualsAndHashCode(of = {"serial_number"})
@Setter
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
    User owner;
    Set<BookedPeriod> booked_periods;
    int counterBooked;
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
		this.image_url = image_url;
		this.owner = null;
		this.booked_periods = new HashSet<>();
	}
	
	public boolean addImageUrl(String ImageUrl) {
		return image_url.add(ImageUrl);
	}
	
	public boolean addFeature(String feature) {
		return features.add(feature);
	}

	public boolean addBookedPeriod(BookedPeriod bookedPeriod) {
		return booked_periods.add(bookedPeriod);
		
	}

	public boolean updateBookPeriod(BookedPeriod bookedPeriod, BookedPeriod bookedPeriodPaid) {
		if (booked_periods.contains(bookedPeriod)) {
			booked_periods.remove(bookedPeriod);
			booked_periods.add(bookedPeriodPaid);
			return true;
		}
		return false;
	}
	
	public void counterBooked() {
		this.counterBooked++;
	}
}
