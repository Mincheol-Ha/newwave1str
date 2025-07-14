package com.example.newwave1str.mapper;

import com.example.newwave1str.web.dto.UserRequestDto;
import com.example.newwave1str.web.dto.UserResponseDto;
import com.example.newwave1str.web.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // 회원가입 요청 DTO -> Entity 변환
    UserEntity toEntity(UserRequestDto dto);

    // 엔티티 -> 회원가입 응답 DTO 변환
    UserResponseDto toUserResponseDto(UserEntity user);


}

