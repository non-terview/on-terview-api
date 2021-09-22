package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationDetailInfoDto {
    private String Type;
    private String example;
    private Integer score;
}
