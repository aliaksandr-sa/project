package telran.java29.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//S
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "bad date format")
public class BadDateFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
