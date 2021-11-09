package site.askephoenix.restapi.chat_room.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.chat_room.model.ChatRoomInfo;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomSelectorDto {
    private Long id;
    private Long interview_id;
    private String title;
    private boolean deleted;

    public ChatRoomSelectorDto(ChatRoomInfo info) {
        this.id = info.getId();
        this.interview_id = info.getInterview().getId();
        this.title = info.getTitle();
        this.deleted = info.getDeleted();
    }

}
