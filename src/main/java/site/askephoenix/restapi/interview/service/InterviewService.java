package site.askephoenix.restapi.interview.service;

import site.askephoenix.restapi.interview.dto.InterviewDto;

import java.util.List;

public interface InterviewService {
    // 해당 인터뷰를 가져옵니다.
    InterviewDto getTargetInterview(Long id);
    // 모든 인터뷰를 가져옵니다.
    List<InterviewDto> loadAllInterview();

}
