package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface TestsListService {
    // 모의시험 문항 가져오기
    HashMap<String, Object> readTestsList(Long companyTestsId);

    Long save(TestsListDto dto, UserInfo userInfo);

    Long update(TestsListDto dto, UserInfo userInfo);
}
