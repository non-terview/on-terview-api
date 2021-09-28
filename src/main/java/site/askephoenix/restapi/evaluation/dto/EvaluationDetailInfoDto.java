package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.evaluation.model.EvaluationDetailInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationTypeList;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EvaluationDetailInfoDto {
    private Long id;
    private List<EvaluationTypeListDto> type;
    private String example;
    private Integer score;

    public EvaluationDetailInfoDto(EvaluationDetailInfo evaluationDetailInfo, List<EvaluationTypeList> evaluationTypeList) {
        this.id = evaluationDetailInfo.getId();
        this.type = evaluationTypeList.stream().map( EvaluationTypeListDto::new ).collect(Collectors.toList());
        this.example = evaluationDetailInfo.getExample();
        this.score = evaluationDetailInfo.getScore();
    }

    public EvaluationDetailInfoDto(
            Long id,
            List<EvaluationTypeListDto> type,
            String example, Integer score
    ) {
        this.id = id;
        this.type = type;
        this.example = example;
        this.score = score;
    }
}
