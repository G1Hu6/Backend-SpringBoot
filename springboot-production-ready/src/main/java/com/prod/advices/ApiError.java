package com.prod.advices;

import com.sun.net.httpserver.HttpPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private HttpStatus httpStatus;

    private String message;

    private List<String> subErrors;
}
