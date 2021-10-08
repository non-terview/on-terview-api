package site.askephoenix.restapi.util;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;

import java.util.List;

@Getter
@Setter
public class SuccessDto<T> {
    // 리스트는 안에 타입을 정의하여 사용하면 됩니다.
    private List<T> loads;
    // 모든 오브젝트는 밑에 overload 하여 사용하면 됩니다.
    private Object load;
    // 키값을 보낼때 사용합니다.
    private Long key;
    // 결과를 출력합니다.
    private String result;

    // dto 1개를 받음
//    public SuccessDto(CompanyTestsDto load){
//        this.load = load;
//        this.result = load.getId().equals(-1L) ? "fail" : "success";
//    }

    // dto 여러개를 받음 (List)
    public SuccessDto(List<T> loads) {
        this.loads = loads;
        this.result = loads.size() == 0 ? "fail" : "success";
    }

    // Info 의 키값 (long) 을 받음
    public SuccessDto(Long key) {
        this.key = key;
        this.result = key.equals(-1L) ? "fail" : "success";
    }


}
