package telran.java29.project.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"first_name", "second_name", "post_date"})
public class Comment {
	String first_name;
	String second_name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate post_date;
	@Setter
	String post;
	
	public Comment(String first_name, String second_name, String post) {
		super();
		this.first_name = first_name;
		this.second_name = second_name;
		this.post = post;
		post_date = LocalDate.now();
	}
	
}
