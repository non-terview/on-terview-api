package site.askephoenix.restapi.resume.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.model.UserInfo;


import java.util.Optional;

public interface ResumeRepository extends JpaRepository<ResumeInfo,Long> {

    @NonNull
    Optional<ResumeInfo> findByUserInfo(UserInfo userInfo);

    @NonNull
    Optional<ResumeInfo> findById(@NonNull Long resumeId);



    @Query("select resume from ResumeInfo resume where resume.isDeleted = false AND resume.userInfo = ?1 ")
    ResumeInfoDto readResume(Long userId);

}
