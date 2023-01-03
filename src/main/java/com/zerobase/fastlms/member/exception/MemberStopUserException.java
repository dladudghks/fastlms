package com.zerobase.fastlms.member.exception;

//이메일 확인된 계정만 로그인될수있도록 예외처리
public class MemberStopUserException extends RuntimeException {
    public MemberStopUserException(String error) {
        super(error);
    }
}
