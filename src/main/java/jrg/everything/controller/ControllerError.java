package jrg.everything.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jrg.everything.errors.Errors;

@Hidden
@RestController
public class ControllerError implements ErrorController  {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println("Error: " + statusCode);
        switch (statusCode) {
            case 404:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Errors.NOT_FOUND.toString());
            case 500:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Errors.INTERNAL_SERVER_ERROR.toString());
            default:
                return ResponseEntity.status(statusCode).body(String.valueOf(statusCode));
        }
    }
    
}
