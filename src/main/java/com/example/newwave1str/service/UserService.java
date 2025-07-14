package com.example.newwave1str.service;

import com.example.newwave1str.jwt.JwtTokenProvider;
import com.example.newwave1str.mapper.UserMapper;
import com.example.newwave1str.repository.UserJpaRepository;
import com.example.newwave1str.web.dto.LoginRequestDto;
import com.example.newwave1str.web.dto.LoginResponseDto;
import com.example.newwave1str.web.dto.UserRequestDto;
import com.example.newwave1str.web.dto.UserResponseDto;
import com.example.newwave1str.web.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder; // DI

    public UserResponseDto signup(UserRequestDto userRequestDto) {
        if (userJpaRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        UserEntity userEntity = userMapper.toEntity(userRequestDto);

        // 비밀번호 암호화!
        userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        UserEntity savedUser = userJpaRepository.save(userEntity);
        return userMapper.toUserResponseDto(savedUser);
    }


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UserEntity user = userJpaRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 틀립니다."));

        // 암호화된 비밀번호와 입력값 비교
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀립니다.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail());

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .token(token)
                .message("로그인 성공!")
                .build();
    }

}
