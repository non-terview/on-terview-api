package site.askephoenix.restapi.resume.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resume_career_detail")
public class ResumeCareerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //회사명
    @Column(name="name")
    private String name;

    //입사일
    @Column(name="join_date")
    private LocalDate joinDate;

    //퇴사일
    @Column(name="resignation_date")
    private LocalDate resignationDate;

    @ManyToOne
    @JoinColumn(name = "resume_info")
    private ResumeInfo resumeInfo;

    @Builder
    public ResumeCareerDetail(Long id, String name,
                              LocalDate joinDate, LocalDate resignationDate,
                              ResumeInfo resumeInfo) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.resignationDate = resignationDate;
        this.resumeInfo = resumeInfo;
    }
}
