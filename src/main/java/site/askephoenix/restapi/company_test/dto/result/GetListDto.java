package site.askephoenix.restapi.company_test.dto.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetListDto {
    private Object list;
    private String state;

    public GetListDto(List<Object> list) {
        this.list = list;
        this.state = list.size() == 0 ? "non-data" : "data";
    }
}
