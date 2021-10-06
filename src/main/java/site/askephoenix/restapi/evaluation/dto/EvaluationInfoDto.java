package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.board.dto.CompanyBoardDto;
import site.askephoenix.restapi.evaluation.model.EvaluationDetailInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;

import java.util.ArrayList;
import java.util.List;

// frontend api
@Getter
@Setter
public class EvaluationInfoDto {
    private Long id;
    private CompanyBoardDto boardDto;
    private int gradations;
    private String title;
    private boolean isDeleted;

    private List<EvaluationDetailInfoDto> details;

    public EvaluationInfoDto() {

    }

    public EvaluationInfoDto(EvaluationInfo evaluationInfo, List<EvaluationDetailInfo> detailInfo) {
        this.gradations = evaluationInfo.getGradations();
        this.title = evaluationInfo.getTitle();
    }

    public EvaluationInfoDto(
            int gradations, String title,
            List<EvaluationDetailInfoDto> details
    ) {
        this.details = details;
        this.gradations = gradations;
        this.title = title;
    }

    public EvaluationInfoDto(EvaluationInfo evaluationInfo) {
        this.id = evaluationInfo.getId();
        this.boardDto = new CompanyBoardDto(evaluationInfo.getBoardInfo());
        this.gradations = evaluationInfo.getGradations();
        this.title = evaluationInfo.getTitle();
        this.isDeleted = evaluationInfo.isDeleted();
        this.details = new ArrayList<>();
    }

    public EvaluationInfoDto(Long id, EvaluationInfo evaluationInfo, List<EvaluationDetailInfo> detailInfo) {
        this.id = id;
        this.gradations = evaluationInfo.getGradations();
        this.title = evaluationInfo.getTitle();
    }

}
