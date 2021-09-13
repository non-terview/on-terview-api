package site.askephoenix.restapi.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FreeBoardDto {
    private String title;
    private String content;
    private String writer;
    private boolean isDeleted;

}
