package site.askephoenix.restapi.resume.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.resume.repository.ResumeRepository;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public Long save(ResumeInfoDto resumeInfoDto,UserInfo userInfo) {
        if (resumeRepository.findByUserInfo(userInfo).
                stream().findAny().isPresent()) {
            return -1L;
        }
        final ResumeInfo resumeInfo = resumeRepository.save(
                ResumeInfo.builder()
                        .userInfo(userInfo)
                        .title(resumeInfoDto.getTitle())
                        .introduction(resumeInfoDto.getIntroduction())
                        .final_edu(resumeInfoDto.getFinal_edu())
                        .edu_status(resumeInfoDto.getEdu_status())
                        .career(resumeInfoDto.getCareer())
                        .certificate(resumeInfoDto.getCertificate())
                        .portfolio(resumeInfoDto.getPortfolio())
                        .job(resumeInfoDto.getJob())
                        .isDeleted(resumeInfoDto.isDeleted())
                        .build()
        );

        return resumeInfo.getId();
    }

    @Override
    public Long update(ResumeInfoDto resumeInfoDto,UserInfo userInfo) {
        if (userInfo==null||userInfo.getId().equals(-1L))
            return -1L;

        ResumeInfo modifyResume = resumeRepository.findByUserInfo(userInfo).orElseGet(
                () -> ResumeInfo.builder().build()
        );

        return resumeRepository.save(
                ResumeInfo.builder()
                        .id(modifyResume.getId())
                        .userInfo(userInfo)
                        .title(resumeInfoDto.getTitle())
                        .introduction(resumeInfoDto.getIntroduction())
                        .final_edu(resumeInfoDto.getFinal_edu())
                        .edu_status(resumeInfoDto.getEdu_status())
                        .career(resumeInfoDto.getCareer())
                        .certificate(resumeInfoDto.getCertificate())
                        .portfolio(resumeInfoDto.getPortfolio())
                        .job(resumeInfoDto.getJob())
                        .createDate(modifyResume.getCreateDate())
                        .updateDate(LocalDateTime.now())
                        .isDeleted(modifyResume.isDeleted())
                        .build()
        ).getId();
    }
}
