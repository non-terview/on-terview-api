package site.askephoenix.restapi.chat.model;

import site.askephoenix.restapi.chat_room.model.ChatRoomInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;

@Entity
@Table(name = "chat_logs")
public class ChatLogInfo {
    // 면접 시, 채팅 기록

    // 채팅 기록 구분
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 채팅 내용
    @Column(name = "comment")
    private String comment;

    // 채팅한 사용자
    @JoinColumn(name = "chat_user")
    @ManyToOne
    private UserInfo chat_user;

    // 채팅방
    @JoinColumn(name = "chat_room")
    @ManyToOne
    private ChatRoomInfo chat_room;

}
