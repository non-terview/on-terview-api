package site.askephoenix.restapi.company_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.user.dto.UserResultDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyTestsDto {
    private Long id;

    // 작성자
    private UserResultDto writer;
    // 응시 문항 & 답지
    private List<TestsListDto> testsListDto;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public CompanyTestsDto(CompanyTestsInfo info) {
        this.id = info.getId();
        this.writer = new UserResultDto(info.getWriter());
        this.createDate = info.getCreateDate();
        this.updateDate = info.getUpdateDate();
    }
}
