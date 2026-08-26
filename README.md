# Street Drop Server

Street Drop Server는 위치 기반 음악 커뮤니티의 인증, 음악 드랍, 알림, 배치 작업과 운영 기능을 제공하는 Spring Boot 백엔드입니다.

## Architecture

```text
backend/
├── streetdrop-api
├── streetdrop-domain
├── streetdrop-common
├── streetdrop-batch
└── streetdrop-admin/
    └── streetdrop-admin-server
```

- `streetdrop-api` — 클라이언트용 HTTP API와 인증
- `streetdrop-domain` — 핵심 도메인 모델과 persistence
- `streetdrop-common` — 공통 설정과 모듈 간 기반 코드
- `streetdrop-batch` — 정기·대량 처리 작업
- `streetdrop-admin` — 운영자용 서버

## Stack

- Java / Spring Boot 3
- Gradle multi-module
- Spring Data JPA / QueryDSL
- Spring Security / JWT
- MySQL / MongoDB
- Flyway
- Firebase Cloud Messaging
- Actuator / Prometheus
- JUnit 5 / JaCoCo

## Build

```bash
cd backend
./gradlew clean build
```

각 실행 모듈의 데이터베이스, 인증, Firebase 설정은 환경별 application profile 또는 환경변수로 제공합니다. 운영 credential은 저장소에 커밋하지 않습니다.
