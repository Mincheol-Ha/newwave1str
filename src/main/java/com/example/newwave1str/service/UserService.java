package com.example.newwave1str.service;

import com.example.newwave1str.jwt.JwtTokenProvider;
import com.example.newwave1str.repository.UserJpaRepository;
import com.example.newwave1str.web.dto.LoginRequestDto;
import com.example.newwave1str.web.dto.LoginResponseDto;
import com.example.newwave1str.web.dto.UserRequestDto;
import com.example.newwave1str.web.dto.UserResponseDto;
import com.example.newwave1str.web.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        // 1. 이메일 중복 체크
        if (userJpaRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 2. 엔티티 생성
        UserEntity userEntity = UserEntity.builder()
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword()) // 비밀번호는 실무에서는 반드시 암호화!
                .build();

        // 3. DB에 저장
        UserEntity savedUser = userJpaRepository.save(userEntity);

        // 4. 응답 Dto로 변환해서 반환
        return UserResponseDto.builder()
                .email(savedUser.getEmail())
                .creatAt(savedUser.getCreatAt())
                .build();
    }
    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        UserEntity user = userJpaRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("아이디또는 비밀번호가 틀립니다."));

        String token = jwtTokenProvider.createToken(user.getEmail());

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .token(token)
                .message("로그인 성공!")
                .build();

    }
}

