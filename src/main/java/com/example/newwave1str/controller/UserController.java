package com.example.newwave1str.controller;

import com.example.newwave1str.service.UserService;
import com.example.newwave1str.web.dto.LoginRequestDto;
import com.example.newwave1str.web.dto.LoginResponseDto;
import com.example.newwave1str.web.dto.UserRequestDto;
import com.example.newwave1str.web.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user-controller", description = "회원 관련 API (호원가입, 로그인)")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "이메일, 비밀번호로 회원가입합니다.")
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

    @Operation(summary = "로그인", description = "가입된 이메일, 비밀번호로 로그인합니다.")
    @PostMapping("login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

}
