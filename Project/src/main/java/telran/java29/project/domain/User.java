package telran.java29.project.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"email"})
@Document(collection = "users")
//S
public class User {
	@Setter
	String first_name;
	@Setter
	String second_name;
	@Id
	String email;
	@Setter
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
		this.password = password;
		this.phone = null;
		registration_date = LocalDate.now();
		comments = new HashSet<>();
		own_cars = new HashSet<>();
		booked_cars = new HashSet<>();
		history = new HashSet<>();
	}
	
	public boolean addComment(Comment comment) {
		return comments.add(comment);
	}
}
