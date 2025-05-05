package com.crm.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class Response{

    private final int status;
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object data;

    private Response(String message, Object data, HttpStatus httpStatus){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.status = httpStatus.value();
    }

    public static Response success(String message, Object data, HttpStatus httpStatus){
        return new Response(message,data, httpStatus);
    }

    public static Response error(String message, HttpStatus status) {
        return new Response(message, null, status);
    }
}
