package site.askephoenix.restapi.resume.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import site.askephoenix.restapi.user.model.UserInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resume_portfolio_detail")
public class ResumePortfolioDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //url , 파일 인지 구분
    @Column(name="type")
    private String type;

    //파일명
    @Column(name="name")
    private String name;

    //포트폴리오 등록일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    @Column(name="create_date")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "resume_info")
    private ResumeInfo resumeInfo;

    @Builder
    public ResumePortfolioDetail(Long id, String type, String name,
                                 LocalDateTime createDate,
                                 ResumeInfo resumeInfo)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.createDate = createDate;
        this.resumeInfo = resumeInfo;
    }

}
