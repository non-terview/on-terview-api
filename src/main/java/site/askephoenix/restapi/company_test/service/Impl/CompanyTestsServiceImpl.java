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
    public List<CompanyTestsDto> readAllTests() {
        return infoListToDtoList(allCompanyTestsInfo());
    }
    // 특정 사용자의 테스트를 가져옴
    @Override
    public List<CompanyTestsDto> readUserTests(UserInfo userInfo){

        return infoListToDtoList(allTestsByWriter(userInfo));
    }

    // 특정 사용자의 모의 시험을 등록합니다.
    @Override
    public Long save(CompanyTestsDto dto, UserInfo userInfo) {
        if (userInfo.getId().equals(-1L)) return -1L;
        return repository.save(CompanyTestsInfo.builder()
                .writer(userInfo)
                .isDeleted(false)
                .build()).getId();
    }


    // repository 에서 모든 row 를 가져옴.
    private List<CompanyTestsInfo> allCompanyTestsInfo() {
        return repository.findAll();
    }

    // repository 에서 특정 사용자의 모든 row 를 가져옴.
    private List<CompanyTestsInfo> allTestsByWriter(UserInfo userInfo){
        return repository.findAllByWriter(userInfo);
    }

    // CompanyTestsInfo 를 CompanyTestsDto 로 변환시킵니다.
    private List<CompanyTestsDto> infoListToDtoList(List<CompanyTestsInfo> list ) {
        return list.stream().map(CompanyTestsDto::new).collect(Collectors.toList());
    }
}
