package site.askephoenix.restapi.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyBoardDto {

    private String company;
    private String writer;
    private String title;
    private String content;
    private String type;
    private boolean isDeleted;


}
