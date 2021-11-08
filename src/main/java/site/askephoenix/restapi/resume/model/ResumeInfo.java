package site.askephoenix.restapi.resume.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resume")
public class ResumeInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //유저 테이블과 조인
    @ManyToOne
    @JoinColumn(name = "user_info")
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

    //희망 업종, 지원 분야
    @Column(name="job")
    private String job;

    //이력서 생성 날짜
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createDate;

    //이력서 수정 날짜
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    //이력서 삭제 상태
    @Column(name = "is_deleted")
    private boolean isDeleted;


    @Builder
    public ResumeInfo(
            Long id,
            UserInfo userInfo,
            String title ,String introduction ,String final_edu,String edu_status,
            String career, String certificate,String portfolio,String job,
            LocalDateTime createDate,LocalDateTime updateDate,
            boolean isDeleted
    ){
        this.id = id;
        this.userInfo = userInfo;
        this.title = title;
        this.introduction = introduction;
        this.final_edu = final_edu;
        this.edu_status = edu_status;
        this.career = career;
        this.certificate = certificate;
        this.portfolio = portfolio;
        this.job = job;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isDeleted = isDeleted;
    }




}

