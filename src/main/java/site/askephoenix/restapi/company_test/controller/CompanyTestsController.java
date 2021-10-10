package site.askephoenix.restapi.company_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.company_test.dto.ResultDtoList;
import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.company_test.dto.result.GetIdDto;
import site.askephoenix.restapi.company_test.dto.result.GetIdDtoList;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.List;

// 모의 시험 컨트롤러
@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class CompanyTestsController {

    /*
     * Get: 가져오기
     * - 등록된 모든 모의 시험 정보 가져오기 (모두)           @GetMapping("")
     * - 로그인 사용자가 작성한 모의시험 리스트 가져오기 (회사)  @GetMapping("/user")
     * - 로그인 사용자가 응시한 모의시험 결과 가져오기 (개인)  @GetMapping("/results/user")
     * - 모의시험 문항 가져오기 (모두)                    @GetMapping("/{test}/list")
     *
     * Post: 생성하기
     * - 모의시험 응시하기 (개인)     @PostMapping("/{test}/results")
     * - 모의시험 등록하기 (회사)     @PostMapping("")
     * - 모의시험 항목 추가하기 (회사) @PostMapping("/{test}/list")
     *
     * Put: 수정하기
     * - 모의시험 항목 수정하기 (회사)     @PutMapping("/{test}/list")
     * - 모의시험 문제 정답 수정하기 (개인) @PutMapping("/{test}/results")
     *
     * Delete: 삭제하기 (isDeleted -> true)
     * - 모의시험 항목 삭제 (회사)         @DeleteMapping("/lists/{list}")
     * - 모의시험 문제 정답 삭제 (개인)     @DeleteMapping("/results/{result}")
     *
     * */


    // 모의시험 등록 정보 관련 서비스
    private final CompanyTestsService service;
    // 모의시험 문항 관련 서비스
    private final TestsListService listService;
    // 모의시험 결과물 관련 서비스
    private final CompanyTestsResultService resultService;


    // 등록된 모든 모의 시험 정보 (모두)
    @GetMapping("")
    public List<CompanyTestsDto> getAllTests() {
        return service.readAllTests();
    }

    // 로그인 사용자가 작성한 모의시험 리스트 (회사)
    @GetMapping("/user")
    public List<CompanyTestsDto> getCompanyTests(
            @LoginUser UserInfo userInfo
    ) {
        return service.readUserTests(userInfo);
    }

    // 로그인 사용자가 응시한 모의시험 결과
    @GetMapping("/results/user")
    public List<ResultDto> getDoTests(
            @LoginUser UserInfo userInfo
    ) {
        return resultService.readResultByTester(userInfo);
    }

    // 모의시험 문항 가져오기 (모두)
    @GetMapping("/{test}/list")
    public List<TestsListDto> getTestList(
            @PathVariable(name = "test") Long test) {
        return listService.readTestsList(test);
    }


    // 모의시험 응시하기 (개인)
    @PostMapping("/{test}/results")
    public GetIdDtoList postResultTests(
            @PathVariable(name = "test") Long test,
            @LoginUser UserInfo userInfo,
            ResultDtoList resultDto
    ) {
        return new GetIdDtoList(resultService.save(test, resultDto.getResultDto(), userInfo));
    }

    // 모의시험 등록하기 (회사)
    @PostMapping("")
    public GetIdDto postCompanyTests(
            @LoginUser UserInfo userInfo
    ) {
        return new GetIdDto(service.save(userInfo));
    }

    // 모의시험 항목 추가하기 (회사)
    @PostMapping("/{test}/list")
    public GetIdDto postTestList(
            @LoginUser UserInfo userInfo,
            TestsListDto dto,
            @PathVariable Long test) {
        dto.setTests_id(test);
        return new GetIdDto(listService.save(dto, userInfo));
    }

    // 모의시험 항목 수정하기 (회사)
    @PutMapping("/lists/{list}")
    public GetIdDto putTestList(
            @LoginUser UserInfo userInfo,
            TestsListDto dto,
            @PathVariable Long list) {
        dto.setId(list);
        return new GetIdDto(listService.update(dto, userInfo));
    }

    // 모의시험 문제 정답 수정하기 (개인)
    @PutMapping("/{test}/results")
    public GetIdDto putResultTests(
            @PathVariable(name = "test") Long test,
            @LoginUser UserInfo userInfo,
            ResultDto dto
    ) {
        return new GetIdDto(resultService.update(test, dto, userInfo));
    }


    // 모의시험 항목 삭제 (회사)
    @DeleteMapping("/lists/{list}")
    public GetIdDto deleteTestList(
            @LoginUser UserInfo userInfo,
            @PathVariable Long list) {
        return new GetIdDto(listService.delete(list, userInfo));
    }

    // 모의시험 문제 정답 삭제 (개인)
    @DeleteMapping("/results/{result}")
    public GetIdDto deleteResultTests(
            @LoginUser UserInfo userInfo,
            @PathVariable Long result
    ) {
        return new GetIdDto(resultService.delete(result, userInfo));
    }

}
