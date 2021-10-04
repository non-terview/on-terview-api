package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface CompanyTestsResultService {

    HashMap<String, Object> readResultByTester(UserInfo tester);
}
