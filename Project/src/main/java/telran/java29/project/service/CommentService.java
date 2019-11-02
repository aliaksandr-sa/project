package telran.java29.project.service;

import telran.java29.project.dto.CommentDto;
//S
public interface CommentService {
	
	Iterable<CommentDto> getLatestComments();

	void addAComment(String serial_number, String post);
}
