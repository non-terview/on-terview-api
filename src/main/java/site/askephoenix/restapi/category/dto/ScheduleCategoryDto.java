package site.askephoenix.restapi.category.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.category.model.CategoryInfo;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;

@Getter
@Setter
@ToString
public class ScheduleCategoryDto {

    //스케쥴 카테고리 dto 입니다 . 그러므로 타입 schedule 입니다 .
    private final String type = "schedule";
    private Long id;
    private String category_name;
    private boolean is_deleted;



    public ScheduleCategoryDto(CategoryInfo category) {
        this.id = category.getId();
        this.category_name = category.getCategoryName();
        this.is_deleted = category.isDeleted();
        ScheduleInfo scheduleInfo;
    }

}
