package site.askephoenix.restapi.resume.service;


import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.user.model.UserInfo;

public interface ResumeService {

    Long save(ResumeInfoDto resumeInfoDto,@LoginUser UserInfo userInfo);

    Long update(ResumeInfo resumeInfo, ResumeInfoDto resumeInfoDto, @LoginUser UserInfo userInfo);
}
