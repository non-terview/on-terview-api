package site.askephoenix.restapi.schedule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.user.model.UserInfo;

public interface ScheduleRepository extends CrudRepository<ScheduleInfo, UserInfo> {


    //아이디로 유저의 스케쥴 검색
    @Query(value = "select s from ScheduleInfo s where s.userInfo= ?1 and s.isDeleted = false and s.id order by desc")
    Page<ScheduleInfo> searchByUser(UserInfo userInfo, Pageable pageable);




}
