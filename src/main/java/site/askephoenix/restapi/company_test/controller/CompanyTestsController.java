package site.askephoenix.restapi.company_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

// 모의 시험 컨트롤러
@RestController
@RequestMapping("/api/tests")
public class CompanyTestsController {

    // 모의 시험 관련 데이터 가져오기
    // 모든 모의 시험
    @GetMapping("")
    public HashMap<String, Object> getAllTests(
            @LoginUser UserInfo userInfo
            ){
        return null;
    }

    // 특정 회사
    @GetMapping("/companys/{company}")
    public HashMap<String, Object> getCompanyTests(
            @LoginUser UserInfo userInfo
    ){
        return null;
    }
    // 로그인 사용자가 응시한 모의시험
    @GetMapping("/do")
    public HashMap<String, Object> getDoTests(
            @LoginUser UserInfo userInfo
    ){
        return null;
    }

    // 특정 모의시험
    @GetMapping("/{test}")
    public HashMap<String, Object> getTargetTests(
            @PathVariable(name = "test") Long test
    ) {
        return null;
    }

    
}
