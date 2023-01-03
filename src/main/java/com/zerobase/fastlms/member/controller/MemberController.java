package com.zerobase.fastlms.member.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    //로그인
    @RequestMapping("/member/login")
    public String login(){

        return "member/login";
    }

    @GetMapping("/member/find/password")
    public String findPassword(){

        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter){

        boolean result = false;

        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e){

        }
        model.addAttribute("result", result);

        return "member/find_password_result";
    }
    
    //회원가입
    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    // request -> WEB -> SERVER
    // response -> SERVER -> WEB
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request
            , MemberInput parameter){

       boolean result = memberService.register(parameter);
       model.addAttribute("result", result);

        System.out.println(parameter.toString());

        return "member/register_complete";
    }

    @GetMapping("/member/email_auth")
    public String emailAuth(Model model, HttpServletRequest request){

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);


        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(){

        return "member/info";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request){

        String uuid = request.getParameter("id");

        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result",result);


        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter){

        boolean result = false;

        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        } catch (Exception e){

        }

        model.addAttribute("result",result);

        return "member/reset_password_result";
    }

}
