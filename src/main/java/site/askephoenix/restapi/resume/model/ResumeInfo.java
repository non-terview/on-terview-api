package site.askephoenix.restapi.resume.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "resume_info")
public class ResumeInfo{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //유저 테이블과 조인
    @ManyToOne
    @JoinColumn(name = "user")
    private UserInfo userInfo;

    //이력서 제목
    @Column(name="title")
    private String title;

    //간단한 소개
    @Column(name="introduction")
    private String introduction;

    //최종학력
    @Column(name="final_edu")
    private String final_edu;

    //최종 학력 상태 ex(재학,졸업,휴학)
    @Column(name="edu_status")
    private String edu_status;

    //경력
    @Column(name="career")
    private String career;

    //자격증
    @Column(name="certificate")
    private String certificate;

    //포트폴리오
    @Column(name="portfolio")
    private String portfolio;

    //이력서 생성 날짜
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDate createDate;

    //이력서 수정 날짜
    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDate updateDate;

    //이력서 삭제 상태
    @Column(name = "is_deleted")
    private boolean isDeleted;



}

