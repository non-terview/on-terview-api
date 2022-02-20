package site.askephoenix.restapi.interview.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.interview.dto.InterviewDto;
import site.askephoenix.restapi.interview.dto.InterviewStartDto;
import site.askephoenix.restapi.interview.service.InterviewService;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {
    private final InterviewService service;
    // 인터뷰(면접) 시에 접근되는 컨트롤러입니다.

    // 면접 정보 가져오기
    @GetMapping("/{interview}")
    public InterviewDto loadInterview(
            @PathVariable Long interview
    ) {
        return new InterviewDto();
    }

    // 면접 시작하기(저장하기)
    @PostMapping("/{interview}")
    public InterviewStartDto startInterview(
            @PathVariable String interview
    ){
        // 시작 시, 필요한 데이터
        // 1. 면접관 정보 (접근 가능한 계정)
        // 2. 면접자 정보 (접근 가능한 계정)
        return null;
    }

    // 모든 면접 정보 가져오기
    @GetMapping("")
    public List<InterviewDto> loadAllInterview(){
        return service.loadAllInterview();
    }


}
