package site.askephoenix.restapi.evaluation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// frontend api
@Getter
@Setter
public class EvaluationInfoDto {
    private int gradations;
    private String title;
    private List<EvaluationDetailInfoDto> details;

}