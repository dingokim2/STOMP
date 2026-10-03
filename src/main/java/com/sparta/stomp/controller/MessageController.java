package com.sparta.stomp.controller;

import com.sparta.stomp.dto.ChatMessageRequest;
import com.sparta.stomp.dto.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    /* 인메모리 메시지 브로커를 쓰던 코드
    @MessageMapping("/chat.{chatRoomId}")
    @SendTo("/subscribe/chat.{chatRoomId}")
    public ChatMessageResponse sendMessage(ChatMessageRequest request, @DestinationVariable Long chatRoomId){
        if(request.username().equals("아봉")){
            throw new RuntimeException("아봉은 채팅을 할 수 없습니다.");
        }

        return new ChatMessageResponse(request.username(), request.content());
    }

    */

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.{chatRoomId}")
    public void sendMessage(@Payload ChatMessageRequest request, @DestinationVariable Long chatRoomId) {
        if(request.username().equals("아봉")){
            throw new RuntimeException("아봉은 채팅을 할 수 없습니다.");
        }

        ChatMessageResponse response = new ChatMessageResponse(request.username(), request.content());

        // 2. RabbitMQ 브로커가 구독 중인 경로로 명시적으로 전송
        // WebSocketConfig에서 설정한 prefix(/topic)와 구독 경로를 조합하여 전달합니다.
        messagingTemplate.convertAndSend("/topic/chat." + chatRoomId, response);
    }

    @MessageExceptionHandler
    public void handleException(RuntimeException e){
        log.info("Exception: {}", e.getMessage());
    }
}
