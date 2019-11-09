package telran.java29.project.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"first_name", "second_name", "post_date"})
@Document(collection = "comments")
@ToString
//S
public class Comment {
	String first_name;
	String second_name;
	String post;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDateTime post_date;
	
	
	public Comment(String first_name, String second_name, String post) {
		this.first_name = first_name;
		this.second_name = second_name;
		this.post = post;
		post_date = LocalDateTime.now();
		System.out.println(post + '2');
	}
	
}
