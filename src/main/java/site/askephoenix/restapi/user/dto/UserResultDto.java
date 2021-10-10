package site.askephoenix.restapi.user.dto;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.user.dto.user.UserDto;
import site.askephoenix.restapi.user.model.UserInfo;

@Getter
@Setter
public class UserResultDto implements UserDto {
    private Long id;
    private String name;

    public UserResultDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.name = userInfo.getName();
    }
}
