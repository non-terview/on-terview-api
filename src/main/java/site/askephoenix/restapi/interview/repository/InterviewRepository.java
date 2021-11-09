package site.askephoenix.restapi.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.interview.model.InterviewInfo;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<InterviewInfo, Long> {

    // id 를 통해 해당 인터뷰를 가져옵니다.
    @Query("select info from InterviewInfo info where info.id = ?1 and info.deleted = false")
    Optional<InterviewInfo> searchById(Long id);

    // 모든 인터뷰를 가져옵니다.
    @Query("select info from InterviewInfo info where info.deleted = false")
    List<InterviewInfo> searchAll();


}
