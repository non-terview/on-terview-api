package site.askephoenix.restapi.resume.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.resume.model.ResumePortfolioDetail;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResumePortfolioDetailDto {
    private Long id;
    private String type;
    private String name;
    private LocalDateTime createDate;
    private ResumeInfoDto resumeInfoDto;

    public ResumePortfolioDetailDto(ResumePortfolioDetail resumePortfolioDetail){
        this.id = resumePortfolioDetail.getId();
        this.type = resumePortfolioDetail.getType();
        this.name = resumePortfolioDetail.getName();
        this.createDate = resumePortfolioDetail.getCreateDate();
        this.resumeInfoDto = new ResumeInfoDto(resumePortfolioDetail.getResumeInfo());

    }
}
