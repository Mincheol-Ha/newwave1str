# 📝 NewWave 1차 프로젝트

> Spring Boot 기반 게시판 서비스  
> 회원가입/로그인, 게시글, 댓글, 좋아요, JWT 인증, Swagger 문서 자동화까지!
>#### 팀원: 강병찬, 최연우, 하민철

---

## 🛠️ 주요 기능

- **회원가입 / 로그인**
    - JWT 기반 인증 및 토큰 발급
- **게시글**
    - 게시글 작성, 수정, 삭제, 전체 조회, 이메일별 조회
- **댓글**
    - 게시글별 댓글 작성, 수정, 삭제
- **좋아요**
    - 게시글 좋아요, 댓글 좋아요 (토글 방식)
- **Swagger (OpenAPI) 연동**
    - API 문서 자동 생성 및 테스트 UI 제공

---

## ⚙️ 기술 스택

- **Backend:** Spring Boot 3.x, Spring Data JPA, Spring Security
- **DB:** MySQL
- **문서화:** springdoc-openapi, Swagger UI
- **빌드:** Gradle
- **기타:** Lombok, MapStruct, JWT(jjwt)

---

## ℹ️ 프로젝트 정보 및 버전

- **프로젝트 버전:** 0.0.1-SNAPSHOT
- **JDK:** Java 21
- **Spring Boot:** 3.5.3
- **Spring Data JPA:** 3.2.x (Spring Boot Starter)
- **Spring Security:** 6.x (Spring Boot Starter)
- **Swagger (springdoc-openapi):** 2.6.0
- **JWT:** jjwt 0.11.5
- **MapStruct:** 1.5.5.Final
- **빌드툴:** Gradle

---

## 🏃‍♂️ 프로젝트 실행 방법

### 1. **환경 변수(.env) 또는 환경설정 등록**
`application.yml`에 DB 접속정보와 JWT 시크릿 키 등 필수 환경변수를 설정하세요.

```yaml
spring:
  datasource:
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
    driver-class-name: ${DRIVER_CLASS_NAME}
    url: ${URL}
  jwt:
    secret: ${JWT_SECRET}

📚 주요 API 명세
Swagger UI에서 전체 경로/파라미터/예시 응답 확인 가능!

구분	메서드	경로	설명
회원가입	POST	/api/signup	회원가입
로그인	POST	/api/login	로그인 및 JWT 토큰 발급
게시글 전체	GET	/api/post	게시글 전체 조회
게시글 작성	POST	/api/post	게시글 작성
게시글 수정	PUT	/api/post/{id}	게시글 수정
게시글 삭제	DELETE	/api/post/{id}	게시글 삭제
댓글 작성	POST	/api/post/{postId}/comment	특정 게시글에 댓글 작성
댓글 수정	PUT	/api/post/{postId}/comment/{id}	댓글 수정
댓글 삭제	DELETE	/api/post/{postId}/comment/{id}	댓글 삭제
게시글 좋아요	POST	/api/post/{postId}/like	게시글 좋아요 토글
댓글 좋아요	POST	/api/comment/{commentId}/like	댓글 좋아요 토글

모든 인증이 필요한 요청은 JWT 토큰(Authorization: Bearer ...) 필요!

🛡️ JWT 인증
로그인 성공 시, JWT Access Token을 응답으로 반환합니다.
이후 인증이 필요한 API 요청 시,
HTTP Header에 아래와 같이 토큰을 전달해야 합니다.
Authorization: Bearer {발급받은_JWT_토큰}

🗂️ 폴더 구조 예시
src
└─ main
    ├─ java
        │ └─ com.example.newwave1str
        │ ├─ config # 스프링/시큐리티 등 환경설정
        │ ├─ controller # REST API 컨트롤러
        │ ├─ filter # JWT 필터 등 시큐리티 필터
        │ ├─ jwt # JWT 토큰 관련 유틸/Provider
        │ ├─ mapper # MapStruct 매퍼
        │ ├─ repository # JPA 리포지토리
        │ ├─ service # 서비스 레이어
        │ ├─ swagger # Swagger/OpenAPI 관련 설정
        │ ├─ web # 엔티티, DTO 등 웹 계층(패키지 역할에 따라 변경)
        │ └─ Newwave1strApplication.java
    └─ resources
    ├─ static
    ├─ templates
    └─ application.yaml

🙋‍♂️ Contact & License
Author: [YOUR NAME]
Email: [YOUR EMAIL]
License: MIT



