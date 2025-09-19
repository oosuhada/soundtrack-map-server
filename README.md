# Street Drop Server

위치 기반 음악 커뮤니티 **Street Drop**의 Spring Boot 백엔드 복원본입니다. 기존 멀티모듈 구조, 도메인, 인증, 배치, 관리자 서버를 유지하면서 현재 Gradle/JDK 환경에서 전체 프로젝트가 다시 빌드되도록 wrapper와 호환성 부분만 정리했습니다.

> 기존 팀 프로젝트의 contributor 목록과 개인 프로필 링크는 README에서 제거했습니다. 서버 도메인과 아키텍처는 원본 구조를 보존했습니다.

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

## Stack

- Java
- Spring Boot 3
- Gradle multi-module
- Spring Data JPA / QueryDSL
- Spring Security / JWT
- MySQL / MongoDB
- Flyway
- JUnit 5 / JaCoCo
- Actuator / Prometheus
- Firebase Cloud Messaging

## Build

```bash
cd backend
./gradlew clean build
```

현재 Gradle 8.14.3 wrapper에서 전체 멀티모듈 build/test가 성공합니다.

```text
BUILD SUCCESSFUL
41 actionable tasks
```

Gradle 9에서 제거될 예정인 deprecated feature 경고가 일부 남아 있으므로, 다음 단계의 modernization에서는 build script deprecation을 개별적으로 정리하는 것이 안전합니다.

## 복원 범위

새로운 개인 음악-memory 도메인은 추가하지 않았습니다. 원래 Street Drop API/domain 구조를 그대로 살리고 현재 JDK/Gradle에서 실행을 막는 부분만 수정했습니다.
