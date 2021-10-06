package site.askephoenix.restapi.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.askephoenix.restapi.user.model.UserInfo;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String auth;
    private String type;

    public UserInfoDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.name = userInfo.getName();
        this.email= userInfo.getEmail();
        // this.password = userInfo.getPassword();      // 보안상 이유로 제외
        this.auth = userInfo.getAuth();
        this.type = userInfo.getType();
    }
}
