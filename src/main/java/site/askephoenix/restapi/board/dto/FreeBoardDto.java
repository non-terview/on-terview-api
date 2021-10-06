package site.askephoenix.restapi.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.category.dto.BoardCategoryDto;

@Getter
@Setter
@ToString
public class FreeBoardDto {

    private Long seq;
    private String writer;
    private String title;
    private String content;
    private BoardCategoryDto category;
    private boolean isDeleted;

    public FreeBoardDto(BoardInfo boardInfo) {
        this.seq = boardInfo.getSeq();
        this.writer = boardInfo.getWriter();
        this.title = boardInfo.getTitle();
        this.content = boardInfo.getContent();
        this.category = new BoardCategoryDto(boardInfo.getCategory());
        this.isDeleted = boardInfo.isDeleted();
    }

}
