package site.askephoenix.restapi.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.category.dto.ScheduleCategoryDto;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PersonalScheduleDto {

    //PersonalScheduleDto 는 content 가 없습니다 .
    private Long id;
    private UserInfo userInfo;
    private ScheduleCategoryDto category;
    private String title;
    private String memo;
    private boolean isDeleted;
    private LocalDate startDate;
    private LocalDate endDate;

    public PersonalScheduleDto(ScheduleInfo scheduleInfo) {
        this.id = scheduleInfo.getId();
        this.userInfo = scheduleInfo.getUserInfo();
        this.category = new ScheduleCategoryDto(scheduleInfo.getCategory());
        this.title = scheduleInfo.getTitle();
        this.memo = scheduleInfo.getMemo();
        this.isDeleted = scheduleInfo.isDeleted();
        this.startDate = scheduleInfo.getStartDate();
        this.endDate = scheduleInfo.getEndDate();
    }

}
