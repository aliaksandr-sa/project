package telran.java29.project.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"latitude", "longitude"})
public class PickUpPlace {
	String country;
	String region;
	String city;
	String street;
	int apartment;
	long zip;
	double latitude;
	double longitude;
}
