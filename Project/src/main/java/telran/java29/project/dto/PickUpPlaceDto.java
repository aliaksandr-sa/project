package telran.java29.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PickUpPlaceDto {
	String country;
	String region;
	String city;
	String street;
	int apartment;
	long zip;
	double latitude;
	double longitude;
}
