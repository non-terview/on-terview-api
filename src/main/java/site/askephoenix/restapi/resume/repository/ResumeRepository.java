package site.askephoenix.restapi.resume.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Collection;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<ResumeInfo,Long> {
    @NonNull
    Optional<ResumeInfo> findByUserInfo(UserInfoDto userInfoDto,@LoginUser UserInfo userInfo);


}
