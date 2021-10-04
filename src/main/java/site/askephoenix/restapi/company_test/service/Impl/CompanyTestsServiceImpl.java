package site.askephoenix.restapi.company_test.service.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsRepository;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTestsServiceImpl implements CompanyTestsService {
    private final CompanyTestsRepository repository;

    // 모든 테스트를 가져옴
    private List<CompanyTestsInfo> allCompanyTestsInfo(){
        return repository.findAll();
    }

    @Override
    public List<CompanyTestsDto> readAllTests(){
        return allCompanyTestsInfo().stream().map(CompanyTestsDto::new).collect(Collectors.toList());

    }

    // 특정 사용자의 테스트를 가져옴
}
