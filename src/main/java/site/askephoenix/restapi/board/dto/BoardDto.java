package site.askephoenix.restapi.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.category.dto.CategoryDto;
import site.askephoenix.restapi.category.model.CategoryInfo;

@Getter
@Setter
@ToString
public class BoardDto {

    private Long seq;
    private String company;
    private String writer;
    private String title;
    private String content;
    private CategoryDto category;
    private String type;
    private boolean isDeleted;


    public BoardDto(BoardInfo boardInfo) {
        this.seq = boardInfo.getSeq();
        this.company = boardInfo.getCompanyName();
        this.writer = boardInfo.getWriter();
        this.title = boardInfo.getTitle();
        this.content = boardInfo.getContent();
        this.category = new CategoryDto(boardInfo.getCategory());
        this.type = boardInfo.getType();
        this.isDeleted = boardInfo.isDeleted();
    }


}
