package telran.java29.project.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"email"})
public class User {
	@Setter
	String first_name;
	@Setter
	String second_name;
	String email;
	String phone;
	LocalDate registration_date;
	Set<Comment> comments;
	Set<Car> own_cars;
	Set<BookedCar> booked_cars;
	Set<BookedCar> history;
	
	public User(String first_name, String second_name) {
		super();
		this.first_name = first_name;
		this.second_name = second_name;
		this.email = null;
		this.phone = null;
		registration_date = LocalDate.now();
		comments = new HashSet<>();
		own_cars = new HashSet<>();
		booked_cars = new HashSet<>();
		history = new HashSet<>();
	}
	
	
}
