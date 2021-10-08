package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface CompanyTestsResultService {

    HashMap<String, Object> readResultByTester(UserInfo tester);

    Long save(Long test, ResultDto dto, UserInfo userInfo);

    Long update(Long test, ResultDto dto, UserInfo userInfo);

    Long delete(Long test, ResultDto dto, UserInfo userInfo);
}
