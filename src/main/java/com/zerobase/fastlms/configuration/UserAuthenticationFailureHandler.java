package com.zerobase.fastlms.configuration;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패하였습니다.";
        if (exception instanceof InternalAuthenticationServiceException) {
            msg = exception.getMessage();
        }
        //org.springframework.security.authentication.InternalAuthenticationServiceException: 이메일 활성화 이후에 로그인을 해주세요.
        //이메일 확인 안된 계정으로 에러 찾아내서 사용


        setUseForward(true);
        setDefaultFailureUrl("/member/login?error=true");
        request.setAttribute("errorMessage",msg);

        System.out.println("로그인에 실패하였습니다. ");


        super.onAuthenticationFailure(request, response, exception);
    }
}
