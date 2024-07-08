package com.BEFresherTP.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;
    private List<String> error;

}
