package site.askephoenix.restapi.resume.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.resume.model.ResumeCareerDetail;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResumeCareerDeatilDto {
    private Long id;
    private String name;
    private LocalDate joinDate;
    private LocalDate resignationDate;
    private ResumeInfoDto resumeInfoDto;

    public ResumeCareerDeatilDto(ResumeCareerDetail resumeCareerDetail) {
        this.id = resumeCareerDetail.getId();
        this.name = resumeCareerDetail.getName();
        this.joinDate = resumeCareerDetail.getJoinDate();
        this.resignationDate = resumeCareerDetail.getResignationDate();
        this.resumeInfoDto = new ResumeInfoDto(resumeCareerDetail.getResumeInfo());
    }
}
