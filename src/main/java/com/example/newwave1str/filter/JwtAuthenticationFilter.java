package com.example.newwave1str.filter;

import com.example.newwave1str.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = jwtTokenProvider.resolveToken(request);
        System.out.println("🟢 JWT: " + jwtToken);

//        if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
//            System.out.println("🟢 토큰 유효성 OK");
//            Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
//            System.out.println("🟢 인증 객체: " + authentication);
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } else {
//            System.out.println("❌ 토큰이 없거나 유효하지 않음");
//        }

        filterChain.doFilter(request, response);
    }
}
