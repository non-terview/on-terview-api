package site.askephoenix.restapi.interview.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;

@Entity
@Table(name = "interviewer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewersInfo {
    // 인터뷰 면접관 리스트를 위한 정보
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 해당 인터뷰
    @ManyToOne
    @JoinColumn(name = "interview")
    private InterviewInfo interview;

    // 면접관
    @JoinColumn(name = "interviewer")
    @ManyToOne
    private UserInfo interviewer;



}
