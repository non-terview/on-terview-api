package site.askephoenix.restapi.chat.model;

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

    //


}
