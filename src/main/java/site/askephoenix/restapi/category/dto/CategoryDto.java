package site.askephoenix.restapi.category.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.category.model.CategoryInfo;

@Getter
@Setter
@ToString
public class CategoryDto {
    private Long id;
    private String type;
    private String category_name;
    private boolean is_deleted;

    public CategoryDto(CategoryInfo category) {
        this.id = category.getId();
        this.category_name = category.getCategoryName();
        this.type = category.getType();
        this.is_deleted = category.isDeleted();
    }


}
