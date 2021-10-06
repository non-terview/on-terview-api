package site.askephoenix.restapi.category.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.category.model.CategoryInfo;

@Getter
@Setter
@ToString
public class BoardCategoryDto {

    //카테고리 타입은 Board입니다 .
    private final String type = "board";
    private Long id;
    private String category_name;
    private boolean is_deleted;

    public BoardCategoryDto(CategoryInfo category) {
        this.id = category.getId();
        this.category_name = category.getCategoryName();
        this.is_deleted = category.isDeleted();
    }

}
