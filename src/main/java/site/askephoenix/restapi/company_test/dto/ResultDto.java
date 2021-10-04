package site.askephoenix.restapi.company_test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.user.dto.UserInfoDto;

@Getter
@Setter
@NoArgsConstructor
public class ResultDto {
    private Long id;
    private CompanyTestsDto companyTestsDto;
    // 테스트를 응시한 유저
    private UserInfoDto tester;
    private int sort_num;
    // 제목
    private String title;
    // 고른 정답
    private String answer;

    public ResultDto(CompanyTestsResultInfo info){
        this.id = info.getId();
        this.sort_num = info.getSort_num();
        this.title = info.getTitle();
        this.answer = info.getAnswer();
    }
}
