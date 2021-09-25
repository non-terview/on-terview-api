package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.evaluation.model.EvaluationTypeList;

@Getter
@Setter
public class EvaluationTypeListDto {
    private Long id;
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
    public EvaluationTypeListDto(
            Long id,
            String name,
            int gradations) {
        this.id=id;
        this.name = name;
        this.gradations = gradations;
    }
    public EvaluationTypeListDto(EvaluationTypeList typeList){
        this.id=typeList.getId();
        this.name=typeList.getName();
        this.gradations=typeList.getGradations();
    }
}
