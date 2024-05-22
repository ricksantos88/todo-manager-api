package br.com.todo.manager.model.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenInvalidException extends RuntimeException {
    private final HttpStatus httpStatus;

    public TokenInvalidException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
