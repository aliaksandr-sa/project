package telran.java29.project.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
//m
@EqualsAndHashCode (of = {"fuel_consumption"})
public class FuelConsumptions {
Double fuel_consumption;

public String getFuel_consumption() {
	return fuel_consumption.toString();
}



}
