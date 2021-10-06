package site.askephoenix.restapi.util;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;

import java.util.List;

@Getter
@Setter
public class SuccessDto {
    private List<Object> loads;
    private Object load;
    private Long key;
    private String result;

    // dto 1개를 받음
    public SuccessDto(CompanyTestsDto load){
        this.load = load;
        this.result = load.getId().equals(-1L) ? "fail" : "success";
    }

    // dto 여러개를 받음 (List)
    public SuccessDto(List<Object> loads) {
        this.loads = loads;
        this.result = loads.size() == 0 ? "fail" : "success";
    }

    // Info 의 키값 (long) 을 받음
    public SuccessDto(Long key) {
        this.key = key;
        this.result = key.equals(-1L) ? "fail" : "success";
    }
}
