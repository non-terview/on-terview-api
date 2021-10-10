package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;
import java.util.List;

public interface CompanyTestsService {
    List<CompanyTestsDto> readAllTests();

    List<CompanyTestsDto> readUserTests(UserInfo userInfo);

    Long save(UserInfo userInfo);
}
