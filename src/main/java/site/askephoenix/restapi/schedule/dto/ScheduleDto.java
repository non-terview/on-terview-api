package site.askephoenix.restapi.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Date;

@Getter
@Setter
@ToString
public class ScheduleDto {

    private UserInfo userInfo;
    private Long id;
    private String title;
    private Date startDate;
    private Date endDate;


}
