package telran.java29.project.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

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
	@Id
	String email;
	String password;
	String phone;
	LocalDate registration_date;
	Set<Comment> comments;
	Set<Car> own_cars;
	Set<BookedCar> booked_cars;
	Set<BookedCar> history;
	
	public User(String first_name, String second_name, String email, String password) {
		this.first_name = first_name;
		this.second_name = second_name;
		this.email = email;
		this.phone = password;
		registration_date = LocalDate.now();
		comments = new HashSet<>();
		own_cars = new HashSet<>();
		booked_cars = new HashSet<>();
		history = new HashSet<>();
	}
	
	
}
