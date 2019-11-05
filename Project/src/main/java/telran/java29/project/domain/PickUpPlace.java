package telran.java29.project.domain;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"place_id"})
//S
public class PickUpPlace {
	@Id
	String place_id;
//	String country;
//	String region;
//	String city;
//	String street;
//	int apartment;
//	long zip;
	double latitude;
	double longitude;
}
