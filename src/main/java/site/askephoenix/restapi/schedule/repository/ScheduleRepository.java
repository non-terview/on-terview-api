package site.askephoenix.restapi.schedule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.user.model.UserInfo;

public interface ScheduleRepository extends CrudRepository<ScheduleInfo, UserInfo> {


    //아이디로 유저의 스케쥴 검색
    @Query(value = "select s from ScheduleInfo s where s.userInfo= ?1 and s.isDeleted = false ")
    Page<ScheduleInfo> searchByUser(UserInfo userInfo, Pageable pageable);

    //스케쥴 제목으로 검색
    @Query(value = "select s from ScheduleInfo s where s.userInfo =?1 and s.title like %?2% and s.isDeleted =false ")
    Page<ScheduleInfo> searchScheduleByTitle(UserInfo userInfo, String title, Pageable pageable);

    //스케쥴 컨텐츠(내용)으로  검색
    @Query(value = "select s from ScheduleInfo s where s.userInfo =?1 and s.content like %?2% and s.isDeleted =false ")
    Page<ScheduleInfo> searchScheduleByContent(UserInfo userInfo, String content, Pageable pageable);

    //스케쥴 메모로 검색
    @Query(value = "select s from ScheduleInfo s where s.userInfo =?1 and s.memo like %?2% and s.isDeleted =false ")
    Page<ScheduleInfo> searchScheduleByMemo(UserInfo userInfo, String memo, Pageable pageable);

    //내용과 제목으로 검색하는 쿼리 or 쓸경우는  괄호로 확실하게 범위를 지정해줘야함
    @Query(value = "select s from ScheduleInfo s where s.userInfo = ?1 and (s.title like %?2% or s.content like %?2%) and s.isDeleted =false ")
    Page<ScheduleInfo> searchScheduleByTitleContent(UserInfo userInfo, String titleOrContent, Pageable pageable);


}
