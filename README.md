# 📊 StatusBoard Backend

> URL 헬스체크 & 시각화 서비스의 백엔드(API) 프로젝트  
> Kotlin + Spring Boot + MariaDB + Swagger 기반으로 구현!

---

## 🎯 프로젝트 소개
- 외부 URL의 상태(응답 시간 등)를 주기적으로 검사
- 검사 결과를 DB에 저장하고, Swagger UI 및 REST API로 조회
- Docker를 이용한 손쉬운 배포 지원

---

## 📥 요구사항
- JDK 21 이상
- Gradle Wrapper (`./gradlew`)
- MariaDB 10.5 이상
- Docker (선택)

---

## 🚀 설치 및 실행

### 1. 로컬 개발 환경
1. **레포지토리 클론**
   ```bash
   git clone https://github.com/seung9526/cloudpilot/backend.git
   cd backend
   ```
환경 변수 설정
프로젝트 루트에 .env 파일 또는 application.yml에 다음 값을 설정.

   ```yaml
   spring:
     datasource:
     url: jdbc:mariadb://<HOST>:<PORT>/<DB_NAME>
     username: <DB_USER>
     password: <DB_PASSWORD>
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ```


애플리케이션 실행

   ```bash
   ./gradlew bootRun
   ```

Swagger UI 접속

   ```bash
    http://localhost:8080/swagger-ui.html
   ```


2. Docker 사용\
   이미지 빌드

   ```bash
   docker build -t statusboard-backend:latest .
   ```
컨테이너 실행

   ```bash
   docker run -d \
--name statusboard-backend \
-e SPRING_DATASOURCE_URL=jdbc:mariadb://<HOST>:<PORT>/<DB_NAME> \
-e SPRING_DATASOURCE_USERNAME=<DB_USER> \
-e SPRING_DATASOURCE_PASSWORD=<DB_PASSWORD> \
-p 8080:8080 \
statusboard-backend:latest
   ```

Swagger UI 확인
   ```bash
   http://localhost:8080/swagger-ui.html
   ```

