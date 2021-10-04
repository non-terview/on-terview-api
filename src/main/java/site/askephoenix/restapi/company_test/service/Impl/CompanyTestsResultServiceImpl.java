package site.askephoenix.restapi.company_test.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsResultRepository;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTestsResultServiceImpl implements CompanyTestsResultService {
    private CompanyTestsResultRepository repository;

    @Override
    public HashMap<String, Object> readResultByTester(UserInfo tester){
        return Maps.newHashMap(ImmutableMap.of(
                "read", allResultByTester(tester),
                "test", "success"
        ));
    }

    // 모든 결과물 가져오기
    private List<CompanyTestsResultInfo> allResult(){
        return repository.findAll();
    }

    // 사용자 결과물 가져오기
    private List<CompanyTestsResultInfo> allResultByTester(UserInfo tester){
        return repository.findAllByTester(tester);
    }

    // CompanyTestsResultInfo 를 ResultDto 로 변환시킵니다.
    private List<ResultDto> infoListToDtoList(List<CompanyTestsResultInfo> list){
        return list.stream().map(ResultDto::new).collect(Collectors.toList());
    }

}
