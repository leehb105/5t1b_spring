package com.spring.otlb.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice// 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시.
public class ExcetionController {

//    404error 페이지는 익셉션이 나지 않으므로, AOP로 보내기 위해서는 익셉션으로 만들어야 한다.
//    그것을 web.xml에서 처리한다.
//    404는 요청을 받았으면 응답을 해주어야 쓰레드에 메모리에서 해제한다.
//    @ResponseStatus를 하지 않으면 상태 오버플로우가 발생한다.
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error404(NoHandlerFoundException e){

        return "/error/error404";
    }

    @ExceptionHandler(Exception.class)
    public String except(Exception e) {

        return "/error/errorPage";
    }
}
