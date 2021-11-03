package site.askephoenix.restapi.resume.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.dto.UserResultDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class ResumeInfoDto {
    private Long id;
    private UserResultDto userInfoDto;
    private String title;
    private String introduction;
    private String final_edu;
    private String edu_status;
    private String career;
    private String certificate;
    private String portfolio;
    private String job;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateDate;
    private boolean isDeleted;


    public ResumeInfoDto(ResumeInfo resumeInfo){
        this.id = resumeInfo.getId();
        this.userInfoDto = new UserResultDto(resumeInfo.getUserInfo());
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
