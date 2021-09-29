package site.askephoenix.restapi.resume.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResumeInfoDto {
    private Long id;
    private UserInfo userInfo;
    private String title;
    private String introduction;
    private String final_edu;
    private String edu_status;
    private String career;
    private String certificate;
    private String portfolio;
    private String job;
    private LocalDate createDate;
    private LocalDate updateDate;
    private boolean isDeleted;

    public ResumeInfoDto(ResumeInfo resumeInfo){
        this.id = resumeInfo.getId();
        this.userInfo = resumeInfo.getUserInfo();
        this.title = resumeInfo.getTitle();
        this.introduction = resumeInfo.getIntroduction();
        this.final_edu = resumeInfo.getFinal_edu();
        this.edu_status = resumeInfo.getEdu_status();
        this.career = resumeInfo.getCareer();
        this.certificate = resumeInfo.getCertificate();
        this.portfolio = resumeInfo.getPortfolio();
        this.job = resumeInfo.getJob();
        this.createDate = resumeInfo.getCreateDate();
        this.updateDate = resumeInfo.getUpdateDate();
        this.isDeleted = resumeInfo.isDeleted();
    }


}
