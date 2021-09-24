package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationTypeListDto {
    private String name;
    private int gradations;
    public EvaluationTypeListDto(){

    }

    public EvaluationTypeListDto(
            String name,
            int gradations) {
        this.name = name;
        this.gradations = gradations;
    }
}
