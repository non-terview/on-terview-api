package site.askephoenix.restapi.interview.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.interview.dto.InterviewDto;
import site.askephoenix.restapi.interview.dto.InterviewStartDto;
import site.askephoenix.restapi.interview.repository.InterviewRepository;
import site.askephoenix.restapi.interview.service.InterviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository repository;

    // 해당 ID 의 인터뷰를 가져옵니다.
    @Override
    public InterviewDto getTargetInterview(Long id) {
        return repository.searchById(id).map(InterviewDto::new).orElseGet(InterviewDto::new);
    }

    // 모든 인터뷰를 가져옵니다.
    @Override
    public List<InterviewDto> loadAllInterview() {
        return repository.searchAll().stream().map(InterviewDto::new).collect(Collectors.toList());
    }

    // 시작 시, 필요한 데이터
    // 1. 면접관 정보 (접근 가능한 계정)
    // 2. 면접자 정보 (접근 가능한 계정)
    private InterviewStartDto InterviewStart(){
        return new InterviewStartDto();
    }

}
