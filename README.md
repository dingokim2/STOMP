# Spring Boot STOMP WebSocket 예제 프로젝트

이 프로젝트는 **Spring Boot STOMP**를 학습하기 위한 예제 프로젝트입니다. 
외부 메시지 브로커로 **RabbitMQ**를 연동하여 웹소켓 메시징을 처리하도록 구성되어 있습니다.

## 🛠️ 사전 준비 (Prerequisites)

스프링 부트 서버를 실행하기 전, 로컬 환경에 **RabbitMQ**를 먼저 실행하고 **STOMP 플러그인**을 활성화해야 합니다. Docker가 설치된 환경에서 진행해 주세요.

### 1. RabbitMQ 컨테이너 실행
터미널(cmd 또는 bash)을 열고 아래 명령어를 입력하여 RabbitMQ 컨테이너를 실행합니다. (STOMP 통신을 위해 `61613` 포트가 열려 있어야 합니다.)

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -p 61613:61613 rabbitmq:3-management
```

### 2. RabbitMQ STOMP 플러그인 활성화
컨테이너가 정상적으로 실행되면, 아래 명령어를 입력하여 RabbitMQ 내부의 STOMP 플러그인을 활성화합니다.

```bash
docker exec -it rabbitmq rabbitmq-plugins enable rabbitmq_stomp
```

> 💡 **Tip:** 관리자 웹 대시보드는 [http://localhost:15672](http://localhost:15672)에서 확인할 수 있습니다. (기본 계정: `guest` / `guest`)

---

## 🚀 실행 및 테스트 방법

### 1. 스프링 서버 실행
- 위의 RabbitMQ 설정이 완료되면, 스프링 부트 애플리케이션을 실행합니다. (기본 포트: `8080`)

### 2. 웹소켓 클라이언트 테스트
서버 내부에 웹소켓 통信을 직접 테스트해 볼 수 있는 HTML 클라이언트 페이지가 포함되어 있습니다. 서버 구동 후 브라우저 주소창에 아래 URL을 입력하여 접속하세요.

👉 **테스트 페이지 주소:** [http://localhost:8080/test.html](http://localhost:8080/test.html)

> 📁 **참고:** 테스트용 HTML 파일은 `src/main/resources/static/test.html` 경로에 위치해 있습니다.
