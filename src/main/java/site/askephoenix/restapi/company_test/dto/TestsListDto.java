package site.askephoenix.restapi.company_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.company_test.model.TestsListInfo;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestsListDto {
    private Long id;
    private String title;
    // 문항 리스트
    private String contents;
    private String answer;

    private LocalDate createDate;
    private LocalDate updateDate;

    public TestsListDto(TestsListInfo info) {
        this.id = info.getId();
        this.title = info.getTitle();
        this.contents = info.getContents();
        this.answer = info.getAnswer();
        this.createDate = info.getCreateDate();
        this.updateDate = info.getUpdateDate();
    }
}
