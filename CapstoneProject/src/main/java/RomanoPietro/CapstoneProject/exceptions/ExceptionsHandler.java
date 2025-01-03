package RomanoPietro.CapstoneProject.exceptions;



import RomanoPietro.CapstoneProject.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	public ErrorsResponseDTO handleBadrequest(BadRequestException ex) {
		return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
	public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex) {
		return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN) // 403
	public ErrorsResponseDTO handleForbidden(AuthorizationDeniedException ex) {
		return new ErrorsResponseDTO("Non hai i permessi per accedere", LocalDateTime.now());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ErrorsResponseDTO handleNotFound(NotFoundException ex) {
		return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	public ErrorsResponseDTO handleGeneric(Exception ex) {
		ex.printStackTrace();
		return new ErrorsResponseDTO("Problema lato server! Giuro che risolverò quanto prima! Ma magari", LocalDateTime.now());
	}
}
