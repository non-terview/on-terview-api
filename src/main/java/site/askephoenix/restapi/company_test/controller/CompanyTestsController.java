package site.askephoenix.restapi.company_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

// 모의 시험 컨트롤러
@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class CompanyTestsController {

    // 모의시험 등록 관련 서비스
    private final CompanyTestsService service;
    // 모의시험 문항 관련 서비스
    private final TestsListService listService;
    // 모의시험 결과물 관련 서비스
    private final CompanyTestsResultService resultService;


    // 모의 시험 관련 데이터 가져오기
    // 모든 모의 시험
    @GetMapping("")
    public HashMap<String, Object> getAllTests(){
        return service.readAllTests();
    }

    // 특정 회사
    @GetMapping("/companys/{company}")
    public HashMap<String, Object> getCompanyTests(
            @LoginUser UserInfo userInfo,
            @PathVariable(name = "company") Long company
    ){
        return service.readUserTests(userInfo);
    }
    // 로그인 사용자가 응시한 모의시험 결과
    @GetMapping("/user")
    public HashMap<String, Object> getDoTests(
            @LoginUser UserInfo userInfo
    ){
        return resultService.readResultByTester(userInfo);
    }

    // 특정 모의시험
    @GetMapping("/{test}")
    public HashMap<String, Object> getTargetTests(
            @PathVariable(name = "test") Long test
    ) {
        return null;
    }
    
}
