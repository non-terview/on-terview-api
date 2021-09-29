package site.askephoenix.restapi.resume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.repository.ResumeRepository;


@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;


    public static Long save(ResumeInfoDto resumeInfoDto) {
        if(resumeRepository.findByUserInfo(resumeInfoDto.getUserInfo()))
        return null;
    }
}
