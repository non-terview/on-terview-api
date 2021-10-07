package site.askephoenix.restapi.resume.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.resume.repository.ResumeRepository;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public Long save(ResumeInfoDto resumeInfoDto, @LoginUser UserInfo userInfo) {
        if(resumeRepository.findByUserInfo(userInfo).
                stream().findAny().isPresent()){
            return -1L;
        }
        final ResumeInfo resumeInfo = resumeRepository.save(
                ResumeInfo.builder()
                        .id(resumeInfoDto.getId())
                        .userInfo(userInfo)
                        .title(resumeInfoDto.getTitle())
                        .introduction(resumeInfoDto.getIntroduction())
                        .final_edu(resumeInfoDto.getFinal_edu())
                        .edu_status(resumeInfoDto.getEdu_status())
                        .career(resumeInfoDto.getCareer())
                        .certificate(resumeInfoDto.getCertificate())
                        .portfolio(resumeInfoDto.getPortfolio())
                        .job(resumeInfoDto.getJob())
                        .createDate(resumeInfoDto.getCreateDate())
                        .updateDate(resumeInfoDto.getUpdateDate())
                        .isDeleted(false)
                        .build()
        );

        return resumeInfo.getId();
    }

    @Override
    public Long update(ResumeInfo resumeInfo,ResumeInfoDto resumeInfoDto,@LoginUser UserInfo userInfo) {

        ResumeInfo modifyResume = resumeRepository.findByResume(resumeInfoDto.getId(), userInfo).orElseGet(
                () -> ResumeInfo.builder().build()
        );
        resumeRepository.save(
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
                        .updateDate(resumeInfoDto.getUpdateDate())
                        .isDeleted(false)
                        .build()
        );
        return null;
        }
}
