package site.askephoenix.restapi.company_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.company_test.model.TestsListInfo;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestsListDto {
    private Long id;
    // 제목
    private String title;
    // 문항 질문
    private String contents;
    // 정답
    private String answer;

    private Long tests_id;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public TestsListDto(TestsListInfo info) {
        this.id = info.getId();
        this.title = info.getTitle();
        this.contents = info.getContents();
        this.answer = info.getAnswer();
        this.createDate = info.getCreateDate();
        this.updateDate = info.getUpdateDate();
    }
}
