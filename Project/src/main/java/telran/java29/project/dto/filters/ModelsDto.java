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
@EqualsAndHashCode (of = {"model"})
public class ModelsDto {
String model;
Set<YearsDto> years;
}
