package site.askephoenix.restapi.company_test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetIdDto {
    private Long id;
    private String state;

    public GetIdDto(Long id){
        this.id = id;
        this.state = id == -1L ? "not-login" : "success";
    }
}
