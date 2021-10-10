package site.askephoenix.restapi.company_test.dto.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetIdDtoList {
    private List<Long> id_list;
    private String state;

    public GetIdDtoList(List<Long> id_list) {
        this.id_list = id_list;
        this.state = id_list.size() == 0 ? "non-data" : "data";
    }
}
