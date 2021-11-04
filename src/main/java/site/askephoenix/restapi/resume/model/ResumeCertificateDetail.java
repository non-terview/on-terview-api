package site.askephoenix.restapi.resume.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resume_certificate_detail")
public class ResumeCertificateDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //자격증 이름
    @Column(name = "name")
    private String name;

    //발행처
    @Column(name = "issue")
    private String issue;

    //취득일
    @Column(name="acquisition_date")
    private LocalDate acquisitionDate;

    @ManyToOne
    @JoinColumn(name = "resume_info")
    private ResumeInfo resumeInfo;

    @Builder
    public ResumeCertificateDetail(Long id, String name, String issue,
                                   LocalDate acquisitionDate,
                                   ResumeInfo resumeInfo)
    {
        this.id = id;
        this.name = name;
        this.issue = issue;
        this.acquisitionDate = acquisitionDate;
        this.resumeInfo = resumeInfo;
    }
}
