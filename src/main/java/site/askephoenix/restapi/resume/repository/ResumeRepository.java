package site.askephoenix.restapi.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<ResumeInfo,Long> {
    Optional<ResumeInfo> findByUserInfo(UserInfo userInfo);
}
