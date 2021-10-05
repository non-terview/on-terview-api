package site.askephoenix.restapi.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.category.dto.CategoryDto;
import site.askephoenix.restapi.category.model.CategoryInfo;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CompanyScheduleDto {

    private Long id;
    private UserInfo userInfo;
    private CategoryDto category;
    private String title;
    private String content;
    private String memo;
    private boolean isDeleted;
    private LocalDate startDate;
    private LocalDate endDate;

    public CompanyScheduleDto(ScheduleInfo scheduleInfo) {
        this.id = scheduleInfo.getId();
        this.userInfo = scheduleInfo.getUserInfo();
        this.category = new CategoryDto(scheduleInfo.getCategory());
        this.title = scheduleInfo.getTitle();
        this.content = scheduleInfo.getContent();
        this.memo = scheduleInfo.getMemo();
        this.isDeleted = scheduleInfo.isDeleted();
        this.startDate = scheduleInfo.getStartDate();
        this.endDate = scheduleInfo.getEndDate();
    }
}
