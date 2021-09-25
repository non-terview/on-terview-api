package site.askephoenix.restapi.status.dto;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.board.dto.BoardDto;
import site.askephoenix.restapi.user.dto.UserInfoDto;

@Getter
@Setter
public class StatusInfoDto {
    private Long id;
    private UserInfoDto userInfoDto;
    private BoardDto boardDto;
    private String state;
    private boolean isDeleted;
}
