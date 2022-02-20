package site.askephoenix.restapi.chat_room.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.chat_room.dto.ChatRoomSelectorDto;
import site.askephoenix.restapi.chat_room.repository.ChatRoomRepository;
import site.askephoenix.restapi.chat_room.service.ChatRoomService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository repository;

    // 해당 인터뷰 채팅방 가져오기
    @Override
    public List<ChatRoomSelectorDto> getChatting(Long id){
        return repository.searchById(id).stream().map(ChatRoomSelectorDto::new).collect(Collectors.toList());
    }

    // 채팅방




}
