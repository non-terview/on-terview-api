package site.askephoenix.restapi.company_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.util.SuccessDto;

import java.util.HashMap;
import java.util.List;

// 모의 시험 컨트롤러
@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class CompanyTestsController {

    /*
     * Get: 가져오기
     * - 등록된 모든 모의 시험 정보 가져오기
     * - 로그인 사용자가 작성한 모의시험 리스트 가져오기
     * - 로그인 사용자가 응시한 모의시험 결과 가져오기 (개인)
     * - 모의시험 문항 가져오기
     *
     * Post: 생성하기
     * - 모의시험 응시하기 (개인)
     * - 모의시험 등록하기 (회사)
     * - 모의시험 항목 추가하기 (회사)
     *
     * Put: 수정하기
     * - 모의시험 항목 수정하기 (회사)
     * - 모의시험 문제 정답 수정하기 (개인)
     *
     * Delete: 삭제하기 (isDeleted -> true)
     * - 모의시험 항목 삭제 (회사)
     * - 모의시험 문제 정답 삭제 (개인)
     *
     * */


    // 모의시험 등록 정보 관련 서비스
    private final CompanyTestsService service;
    // 모의시험 문항 관련 서비스
    private final TestsListService listService;
    // 모의시험 결과물 관련 서비스
    private final CompanyTestsResultService resultService;


    // 모의 시험 관련 데이터 가져오기
    // 등록된 모든 모의 시험 정보
    @GetMapping("")
    public HashMap<String, Object> getAllTests() {
        return service.readAllTests();
    }

    // 로그인 사용자가 작성한 모의시험 리스트
    @GetMapping("/user")
    public SuccessDto<CompanyTestsDto> getCompanyTests(
            @LoginUser UserInfo userInfo
    ) {
        return new SuccessDto<>(service.readUserTests(userInfo));
    }

    // 로그인 사용자가 응시한 모의시험 결과
    @GetMapping("/results/user")
    public HashMap<String, Object> getDoTests(
            @LoginUser UserInfo userInfo
    ) {
        return resultService.readResultByTester(userInfo);
    }

    // 모의시험 문항 가져오기
    @GetMapping("/{test}/list")
    public HashMap<String, Object> getTestList(
            @PathVariable(name = "test") Long test) {
        return listService.readTestsList(test);
    }


    // 모의시험 응시하기
    @PostMapping("/{test}/results")
    public SuccessDto<Long> postResultTests(
            @PathVariable(name = "test") Long test,
            @LoginUser UserInfo userInfo,
            ResultDto dto
    ) {
        return new SuccessDto<>(resultService.save(test, dto, userInfo));
    }

    // 모의시험 등록하기 (회사)
    @PostMapping("")
    public SuccessDto<Long> postCompanyTests(
            CompanyTestsDto dto,
            @LoginUser UserInfo userInfo
    ) {
        return new SuccessDto<>(service.save(dto, userInfo));
    }

    // 모의시험 항목 추가하기 (회사)
    @PostMapping("/{test}/list")
    public SuccessDto<Long> postTestList(
            @LoginUser UserInfo userInfo,
            TestsListDto dto,
            @PathVariable Long test) {
        dto.setTests_id(test);
        return new SuccessDto<>(listService.save(dto, userInfo));
    }

    // 모의시험 항목 수정하기 (회사)
    @PutMapping("/{test}/list")
    public SuccessDto<Long> putTestList(
            @LoginUser UserInfo userInfo,
            TestsListDto dto,
            @PathVariable Long test) {
        dto.setTests_id(test);
        return new SuccessDto<>(listService.update(dto, userInfo));
    }

    // 모의시험 문제 정답 수정하기 (개인)
    @PutMapping("/{test}/results")
    public SuccessDto<Long> putResultTests(
            @PathVariable(name = "test") Long test,
            @LoginUser UserInfo userInfo,
            ResultDto dto
    ) {
        return new SuccessDto<>(resultService.update(test, dto, userInfo));
    }


    // 모의시험 항목 삭제 (회사)
    @DeleteMapping("/{test}/list")
    public SuccessDto<Long> deleteTestList(
            @LoginUser UserInfo userInfo,
            TestsListDto dto,
            @PathVariable Long test) {
        dto.setTests_id(test);
        return new SuccessDto<>(listService.delete(dto, userInfo));
    }

    // 모의시험 문제 정답 삭제 (개인)
    @DeleteMapping("/{test}/results")
    public SuccessDto<Long> deleteResultTests(
            @PathVariable(name = "test") Long test,
            @LoginUser UserInfo userInfo,
            ResultDto dto
    ) {
        return new SuccessDto<>(resultService.delete(test, dto, userInfo));
    }

}
