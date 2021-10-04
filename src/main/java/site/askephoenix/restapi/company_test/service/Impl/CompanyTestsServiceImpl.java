package site.askephoenix.restapi.company_test.service.Impl;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.CompanyTestsDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsRepository;
import site.askephoenix.restapi.company_test.service.CompanyTestsService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTestsServiceImpl implements CompanyTestsService {
    private final CompanyTestsRepository repository;

    // 모든 테스트를 가져옴
    @Override
    public HashMap<String, Object> readAllTests() {
        return Maps.newHashMap(ImmutableMap.of(
                "read", infoListToDtoList(allCompanyTestsInfo()), "test", "success"
        ));
    }
    // 특정 사용자의 테스트를 가져옴
    @Override
    public HashMap<String, Object> readUserTests(UserInfo userInfo){

        return Maps.newHashMap(ImmutableMap.of(
                "read", infoListToDtoList(allTestsByWriter(userInfo)), "test", "success"
        ));
    }
    // repository 에서 모든 row 를 가져옴.
    private List<CompanyTestsInfo> allCompanyTestsInfo() {
        return repository.findAll();
    }

    // repository 에서 특정 사용자의 모든 row 를 가져옴.
    private List<CompanyTestsInfo> allTestsByWriter(UserInfo userInfo){
        return repository.findAllByWriter(userInfo);
    }

    // repository 에서

    // CompanyTestsInfo 를 CompanyTestsDto 로 변환시킵니다.
    private List<CompanyTestsDto> infoListToDtoList(List<CompanyTestsInfo> list ) {
        return list.stream().map(CompanyTestsDto::new).collect(Collectors.toList());
    }
}
