package site.askephoenix.restapi.chat_room.model;

import javax.persistence.*;

@Entity
@Table(name="chat_room")
public class ChatRoomInfo {
    // 채팅방 정보

    // 채팅방 구분번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 해당 면접(게시판) 정보


    // 채팅방 제목

}
