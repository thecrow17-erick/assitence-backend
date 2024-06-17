package crow.uagrm.parcial.error;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import crow.uagrm.parcial.error.dto.ErrorMessage;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.error.exception.UnauthorizedException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler{

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> messages = ex.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList();
    ErrorMessage errorMessage = new ErrorMessage(
      HttpStatus.BAD_REQUEST.value(), 
      messages, 
      HttpStatus.BAD_REQUEST
    );
    return errorMessage;
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorMessage handleResourceNotFoundException(NotFoundException ex) {
    List<String> messages =Collections.singletonList(ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(
      HttpStatus.NOT_FOUND.value(),
      messages , 
      HttpStatus.NOT_FOUND
    );
  
    return errorMessage;
  } 


  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorMessage handleResourceUnauthorizedException(UnauthorizedException ex) {
    List<String> messages =Collections.singletonList(ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(
      HttpStatus.UNAUTHORIZED.value(),
      messages, 
      HttpStatus.UNAUTHORIZED
    );
    return errorMessage;
  } 

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleResourceBadRequestException(BadRequestException ex) {
    List<String> messages =Collections.singletonList(ex.getMessage());
    ErrorMessage errorMessage = new ErrorMessage(
      HttpStatus.BAD_REQUEST.value(),
      messages , 
      HttpStatus.BAD_REQUEST
    );
    return errorMessage;
  } 

}
