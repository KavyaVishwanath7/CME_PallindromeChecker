package com.cme.palindrome.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
class APIError {

    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<String> errors;

    APIError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}