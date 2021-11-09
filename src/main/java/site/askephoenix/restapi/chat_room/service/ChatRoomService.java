package site.askephoenix.restapi.chat_room.service;

import org.springframework.stereotype.Service;
import site.askephoenix.restapi.chat_room.dto.ChatRoomSelectorDto;

import java.util.List;

public interface ChatRoomService {
    // 채팅방 가져오기
    List<ChatRoomSelectorDto> getChatting(Long id);
}
