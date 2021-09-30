package site.askephoenix.restapi.resume.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.resume.dto.ResumeInfoDto;
import site.askephoenix.restapi.resume.model.ResumeInfo;
import site.askephoenix.restapi.resume.repository.ResumeRepository;
import site.askephoenix.restapi.resume.service.ResumeService;
import site.askephoenix.restapi.user.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public Long save(ResumeInfoDto resumeInfoDto) {
        if(resumeRepository.findByUserInfo(resumeInfoDto.getUserInfoDto()).
                stream().findAny().isPresent()){
            return -1L;
        }
        final ResumeInfo resumeInfo = resumeRepository.save(
                ResumeInfo.builder()
                        .id(resumeInfoDto.getId())
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
}
