package site.askephoenix.restapi.resume.service;


import site.askephoenix.restapi.resume.dto.ResumeInfoDto;

public interface ResumeService {

    Long save(ResumeInfoDto resumeInfoDto);
}
