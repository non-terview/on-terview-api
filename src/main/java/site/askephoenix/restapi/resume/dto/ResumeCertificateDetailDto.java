package site.askephoenix.restapi.resume.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.resume.model.ResumeCertificateDetail;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResumeCertificateDetailDto {
    private Long id;
    private String name;
    private String issue;
    private LocalDate acquisitionDate;
    private ResumeInfoDto resumeInfoDto;

    public ResumeCertificateDetailDto(ResumeCertificateDetail resumeCertificateDetail){
        this.id = resumeCertificateDetail.getId();
        this.name = resumeCertificateDetail.getName();
        this.issue = resumeCertificateDetail.getIssue();
        this.acquisitionDate = resumeCertificateDetail.getAcquisitionDate();
        this.resumeInfoDto = new ResumeInfoDto(resumeCertificateDetail.getResumeInfo());
    }
}
