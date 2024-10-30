package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.error.ErrorRs;
import hbs.hotel_booking_servise.error.IncorrectRequestEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(EntityNotFoundEx.class)
    public ResponseEntity<ErrorRs> notFound(EntityNotFoundEx ex) {
        log.debug("handles exception with notFound() method");


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorRs(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(IncorrectRequestEx.class)
    public ResponseEntity<ErrorRs> incorrectRequest(IncorrectRequestEx ex) {
        log.debug("handles exception with incorrectRequest() method");

        return ResponseEntity
                .status(400)
                .body(new ErrorRs(ex.getLocalizedMessage()));
    }




    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRs> unhandledError(RuntimeException ex) {
        log.debug("handles exception with unhandledError() method");

        return ResponseEntity
                .status(500)
                .body(new ErrorRs("SERVER EXCEPTION, TRY AGAIN LATER"));
    }
}
