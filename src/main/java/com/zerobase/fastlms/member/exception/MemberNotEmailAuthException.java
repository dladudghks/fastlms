package com.zerobase.fastlms.member.exception;

//이메일 확인된 계정만 로그인될수있도록 예외처리
public class MemberNotEmailAuthException extends RuntimeException {
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
