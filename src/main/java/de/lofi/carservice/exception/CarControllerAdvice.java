package de.lofi.carservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CarControllerAdvice {

	/**
	 * 
	 * @return
	 */
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handleCarNotFoundException(RuntimeException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    /**
     * 
     * @return
     */
    @ExceptionHandler(CarAlreadyExistingException.class)
    public ResponseEntity<String> handleCarAlreadyExistingException(RuntimeException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
       
}