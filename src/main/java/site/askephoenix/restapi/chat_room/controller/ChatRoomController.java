package site.askephoenix.restapi.chat_room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.chat_room.dto.ChatRoomSelectorDto;
import site.askephoenix.restapi.chat_room.service.ChatRoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-room")
public class ChatRoomController {
    private final ChatRoomService service;

    // 채팅방 목록 가져오기
    @GetMapping("/boards/{board}")
    public List<ChatRoomSelectorDto> loadRoomSelector(
            @PathVariable Long board
    ){
        return service.getChatting(board);
    }

    // 채팅방 접속하기(면접 진행)

    // 채팅방 나가기

}
