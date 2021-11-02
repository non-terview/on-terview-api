package site.askephoenix.restapi.resume.service;


import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.model.UserInfo;

public interface ResumeService {

    Long save(ResumeInfoDto resumeInfoDto,@LoginUser UserInfo userInfo);

    Long update(ResumeInfoDto resumeInfoDto, @LoginUser UserInfo userInfo);

    Long deleteResume(ResumeInfoDto resumeInfoDto,@LoginUser UserInfo userInfo);

    ResumeInfoDto readResumeInfo(ResumeInfoDto resumeInfoDto , Long userId);
}
