package telran.java29.project.domain;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"serial_number"})
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
    PickUpPlace pick_up_place;
    Set<String> image_url;
    User owner;
    Set<BookedPeriod> booked_periods;
}
