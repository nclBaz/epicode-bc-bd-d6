package riccardogulin.d6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import riccardogulin.d6.dto.errors.ErrorDTO;
import riccardogulin.d6.dto.errors.ErrorWithListDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	public ErrorWithListDTO handleBadRequest(ValidationException ex) {
		return new ErrorWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsList());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ErrorDTO handleNotFound(NotFoundException ex) {
		return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	public ErrorDTO handleGenericError(Exception ex) {
		ex.printStackTrace();
		return new ErrorDTO("C'è stato un errore lato server, lo risolveremo presto", LocalDateTime.now());
	}
}
