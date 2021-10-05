package site.askephoenix.restapi.company_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyTestsDto {
    private Long id;

    // 작성자
    private UserInfo writer;
    // 응시 문항 & 답지
    private List<TestsListDto> testsListDto;

    private LocalDate createDate;
    private LocalDate updateDate;

    public CompanyTestsDto(CompanyTestsInfo info) {
        this.id = info.getId();
        this.writer = info.getWriter();
        this.createDate = info.getCreateDate();
        this.updateDate = info.getUpdateDate();
    }
}
