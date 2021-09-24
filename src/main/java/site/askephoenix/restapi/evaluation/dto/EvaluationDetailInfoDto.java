package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EvaluationDetailInfoDto {
    private List<EvaluationTypeListDto> type;
    private String example;
    private Integer score;

    public EvaluationDetailInfoDto(){

    }

    public EvaluationDetailInfoDto(
            List<EvaluationTypeListDto> type,
            String example, Integer score
    ) {
        this.type = type;
        this.example = example;
        this.score = score;
    }
}
