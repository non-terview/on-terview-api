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

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;


    //저장
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

    
    //수정
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

    
    //삭제
    @Override
    public Long deleteResume(ResumeInfoDto resumeInfoDto, UserInfo userInfo) {
        if (userInfo==null||userInfo.getId().equals(-1L))
            return -1L;

        ResumeInfo modifyResume = resumeRepository.findByUserInfo(userInfo).orElseGet(
                () -> ResumeInfo.builder().build()
        );

        return resumeRepository.save(
                ResumeInfo.builder()
                        .id(modifyResume.getId())
                        .userInfo(modifyResume.getUserInfo())
                        .title(modifyResume.getTitle())
                        .introduction(modifyResume.getIntroduction())
                        .final_edu(modifyResume.getFinal_edu())
                        .edu_status(modifyResume.getEdu_status())
                        .career(modifyResume.getCareer())
                        .certificate(modifyResume.getCertificate())
                        .portfolio(modifyResume.getPortfolio())
                        .job(modifyResume.getJob())
                        .createDate(modifyResume.getCreateDate())
                        .updateDate(LocalDateTime.now())
                        .isDeleted(true)
                        .build()
        ).getId();
    }

    @Override
    public ResumeInfoDto readResumeInfo(Long userId) {

        return resumeRepository.readResume(userRepository.findById(userId).orElseGet(
                ()-> UserInfo.builder().id(-1L).build()
        ));
    }


}
