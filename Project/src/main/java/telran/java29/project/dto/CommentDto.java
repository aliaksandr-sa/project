package telran.java29.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
//S
public class CommentDto {
	String first_name;
	String second_name;
	LocalDateTime post_date;
	String post;

}
