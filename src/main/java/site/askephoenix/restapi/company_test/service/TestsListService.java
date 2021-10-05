package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.TestsListDto;

import java.util.HashMap;
import java.util.List;

public interface TestsListService {
    List<TestsListDto> loadTestsListDto();

    // 모의시험 문항 가져오기
    HashMap<String, Object> readTestsList(Long companyTestsId);
}
