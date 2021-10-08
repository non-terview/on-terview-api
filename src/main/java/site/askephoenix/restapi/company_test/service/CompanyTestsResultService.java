package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;
import java.util.List;

public interface CompanyTestsResultService {

    List<ResultDto> readResultByTester(UserInfo tester);

    Long save(Long test, ResultDto dto, UserInfo userInfo);

    Long update(Long test, ResultDto dto, UserInfo userInfo);

    Long delete(Long test, ResultDto dto, UserInfo userInfo);
}
