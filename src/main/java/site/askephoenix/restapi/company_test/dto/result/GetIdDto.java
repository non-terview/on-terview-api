package site.askephoenix.restapi.company_test.dto.result;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.util.DefaultMessage;

@Getter
@Setter
public class GetIdDto {
    private Long id;
    private String state;

    public GetIdDto(Long id) {
        this.id = id;
        this.state = DefaultMessage.getMessage(id) == null ? "success" : DefaultMessage.getMessage(id);
    }
}
