package site.askephoenix.restapi.interview.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;

@Entity
@Table(name = "interview_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewInfo {
    // 인터뷰 정보 - 면접 생성, 접근

    // 인터뷰 등록 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 인터뷰 등록 회사
    @JoinColumn(name = "company")
    @ManyToOne
    private UserInfo company;

    // 인터뷰 참여자(외부 참조) - applicant


    // 인터뷰 면접자(외부 참조) - interviewers

    // 인터뷰 종료 여부
    @Column(name = "deleted")
    private Boolean deleted;

}
