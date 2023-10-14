package com.app.studentmanagementsystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.Serial;


@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String path;

    public BadRequestException(String message, String path) {
        super(message);
        this.path = path;
    }

}