package site.askephoenix.restapi.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.user.dto.user.UserDto;
import site.askephoenix.restapi.user.model.UserInfo;

@Getter
@Setter
@NoArgsConstructor
public class UserResultDto implements UserDto {
    private Long id;
    private String name;
    private String email;
    private String type;

    public UserResultDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.email = userInfo.getEmail();
        this.name = userInfo.getName();
        this.type = userInfo.getType();
    }
}
