package com.spring.otlb.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExcetionController {

    @ExceptionHandler(Exception.class)
    public String error404(NoHandlerFoundException e){

        return "";
    }
}
