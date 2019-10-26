package telran.java29.project.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
//m
public class UpdateCarDto {
	Set<String> features;
	String car_class;
	Double price_per_day;
	Double distance_included;
	PickUpPlaceDto pick_up_place;
	Set<String> image_url;
}
