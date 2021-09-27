package site.askephoenix.restapi.schedule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.user.model.UserInfo;

public interface ScheduleRepository extends CrudRepository<ScheduleInfo, UserInfo> {

    //
    Page<ScheduleInfo> findAllByUserInfo(UserInfo userInfo, Pageable pageable);

}
