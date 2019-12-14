package telran.java29.project.dto.filters;

import java.util.Set;

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
@EqualsAndHashCode (of = {"wheels_drive"})
public class WheelsDrivesDto {
String wheels_drive;
Set<FuelConsumptions> fuel_consumptions;
}
