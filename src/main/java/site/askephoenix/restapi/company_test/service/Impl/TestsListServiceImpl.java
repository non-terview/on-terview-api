package site.askephoenix.restapi.company_test.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.model.TestsListInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsRepository;
import site.askephoenix.restapi.company_test.repository.TestsListInfoRepository;
import site.askephoenix.restapi.company_test.service.TestsListService;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestsListServiceImpl implements TestsListService {
    private final TestsListInfoRepository repository;
    private final CompanyTestsRepository testsRepository;

    @Override
    public List<TestsListDto> loadTestsListDto() {
        return AllTestsListInfo().stream().map(TestsListDto::new).collect(Collectors.toList());
    }
    // 모의시험 문항 가져오기
    @Override
    public HashMap<String, Object> readTestsList(Long companyTestsId) {
        return Maps.newHashMap(ImmutableMap.of(
                "read", infoListToDtoList(
                        Objects.requireNonNull(
                                AllTestsByThat(companyTestsInfoFindById(companyTestsId))))
                , "test", "success"));
    }

    // 모든 TestsList 를 가져옵니다.
    private List<TestsListInfo> AllTestsListInfo() {
        return repository.findAll();
    }

    // TestsListInfo 를 TestsListDto 로 변환시킵니다.
    private List<TestsListDto> infoListToDtoList(List<TestsListInfo> list) {
        return list.stream().map(TestsListDto::new).collect(Collectors.toList());
    }

    // info find tests by id
    private CompanyTestsInfo companyTestsInfoFindById(Long id) {
        return testsRepository.findById(id).orElseGet(() -> CompanyTestsInfo.builder().build());
    }

    // 특정
    private List<TestsListInfo> AllTestsByThat(CompanyTestsInfo info) {
        return repository.findAllByTests(info);
    }

}
