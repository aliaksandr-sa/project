package telran.java29.project.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//S
public class OwnCarDto {
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
    Set<BookedPeriodDto> booked_periods;
}
