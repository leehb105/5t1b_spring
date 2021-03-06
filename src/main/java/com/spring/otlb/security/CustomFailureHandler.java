package com.spring.otlb.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Service
public class CustomFailureHandler implements AuthenticationFailureHandler {

    private String errorMsg;
    private String defaultFailureUrl;

    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        this.defaultFailureUrl = defaultFailureUrl;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        defaultFailureUrl = "/emp/empLogin.do?error=true&errorMsg=";

        errorMsg = "오류입니다";
        if(exception instanceof BadCredentialsException){
            errorMsg = "아이디 또는 비밀번호가 일치하지 않습니다.";

        }

        request.getRequestDispatcher(defaultFailureUrl + errorMsg).forward(request, response);

    }
}
