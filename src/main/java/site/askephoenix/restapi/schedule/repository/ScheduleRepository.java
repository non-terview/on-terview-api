package site.askephoenix.restapi.schedule.repository;

import org.springframework.data.repository.CrudRepository;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.user.model.UserInfo;

public interface ScheduleRepository extends CrudRepository<ScheduleInfo, UserInfo> {

    //

}
