package site.askephoenix.restapi.chat_room.model;

import lombok.Getter;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.interview.model.InterviewInfo;

import javax.persistence.*;

@Entity
@Table(name="chat_room")
@Getter
public class ChatRoomInfo {
    // 채팅방 정보

    // 채팅방 구분번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 해당 면접(interview) 정보
    @JoinColumn(name = "interview")
    @ManyToOne
    private InterviewInfo interview;


    // 채팅방 제목
    @Column(name = "title")
    private String title;

    // 삭제 여부
    @Column(name = "deleted")
    private Boolean deleted;

}
