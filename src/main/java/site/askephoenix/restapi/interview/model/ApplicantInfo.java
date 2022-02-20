package site.askephoenix.restapi.interview.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;

@Entity
@Table(name = "applicant_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantInfo {
    // 면접 지원자 리스트를 위한 정보

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 해당 인터뷰
    @ManyToOne
    @JoinColumn(name = "interview")
    private InterviewInfo interview;

    // 면접자
    @ManyToOne
    @JoinColumn(name = "applicant_user")
    private UserInfo applicant_user;



}
