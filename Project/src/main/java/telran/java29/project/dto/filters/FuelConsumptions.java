package telran.java29.project.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//m
@EqualsAndHashCode (of = {"fuel_consumption"})
public class FuelConsumptions {
String fuel_consumption;
}
