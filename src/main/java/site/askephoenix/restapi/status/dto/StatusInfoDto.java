package site.askephoenix.restapi.status.dto;

import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.board.dto.BoardDto;
import site.askephoenix.restapi.status.model.StatusInfo;
import site.askephoenix.restapi.user.dto.UserInfoDto;

@Getter
@Setter
public class StatusInfoDto {
    private Long id;
    private UserInfoDto userInfoDto;
    private BoardDto boardDto;
    private String state;
    private boolean isDeleted;

    public StatusInfoDto(StatusInfo info) {
        this.id = info.getId();
        this.userInfoDto = new UserInfoDto( info.getUserInfo() );
        this.boardDto = new BoardDto( info.getBoardInfo() );
        this.state = info.getState();
        this.isDeleted = info.isDeleted();
    }
}
