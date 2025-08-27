# Kotlin + Spring Boot Dockerfile
FROM openjdk:21-slim

LABEL maintainer="StatusBoard_ProJect"

ENV TZ=Asia/Seoul
WORKDIR /app

# Gradle 파일 먼저 복사 → 의존성 캐시
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# 취약점 패치: zlib1g 업그레이드
RUN apt-get update && apt-get install -y --only-upgrade zlib1g && rm -rf /var/lib/apt/lists/*

# 전체 프로젝트 복사
COPY . .
RUN chmod +x gradlew

# 빌드
RUN ./gradlew clean bootJar -x test --no-daemon -PjarName=statusboard.jar
CMD ["java", "-jar", "build/libs/statusboard.jar"]

# 실행 (JAR 파일 이름 자동 인식)
CMD ["sh", "-c", "java -jar build/libs/$(ls build/libs | grep -v plain | head -n 1)"]
