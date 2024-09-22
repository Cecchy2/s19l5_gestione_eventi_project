package dariocecchinato.s19l5_gestione_eventi_project.exceptions;


import dariocecchinato.s19l5_gestione_eventi_project.payloads.ErrorPayloadDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorPayloadDTO handleBadRequest(BadRequestException ex) {
		return new ErrorPayloadDTO(ex.getMessage(), LocalDateTime.now());
	}
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorPayloadDTO handleResponseStatus(ResponseStatusException ex){
		return new ErrorPayloadDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
	public ErrorPayloadDTO handleUnauthorized(UnauthorizedException ex) {
		return new ErrorPayloadDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN) // 403
	public ErrorPayloadDTO handleForbidden(AuthorizationDeniedException ex) {
		return new ErrorPayloadDTO("Non hai i permessi per accedere", LocalDateTime.now());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ErrorPayloadDTO handleNotFound(NotFoundException ex) {
		return new ErrorPayloadDTO(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	public ErrorPayloadDTO handleGenericErrors(Exception ex) {
		ex.printStackTrace(); 
		return new ErrorPayloadDTO("Problema lato server, giuro che lo risolveremo presto!", LocalDateTime.now());
	}
}
