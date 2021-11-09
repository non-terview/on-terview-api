package site.askephoenix.restapi.interview.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.interview.model.InterviewInfo;

@Getter
@Setter
@NoArgsConstructor
public class InterviewDto {
    private Long id;

    public InterviewDto(InterviewInfo info) {
        this.id = info.getId();
    }
}
