package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface CompanyTestsService {
    HashMap<String, Object> readAllTests();

    HashMap<String, Object> readUserTests(UserInfo userInfo);

    Long save(CompanyTestsDto dto, UserInfo userInfo);
}
