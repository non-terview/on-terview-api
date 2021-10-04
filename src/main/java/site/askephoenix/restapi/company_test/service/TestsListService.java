package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.TestsListDto;

import java.util.List;

public interface TestsListService {
    List<TestsListDto> loadTestsListDto();
}
