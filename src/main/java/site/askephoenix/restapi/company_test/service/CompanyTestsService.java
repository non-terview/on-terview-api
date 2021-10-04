package site.askephoenix.restapi.company_test.service;

import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;

import java.util.List;

public interface CompanyTestsService {
    List<CompanyTestsDto> readAllTests();
}
