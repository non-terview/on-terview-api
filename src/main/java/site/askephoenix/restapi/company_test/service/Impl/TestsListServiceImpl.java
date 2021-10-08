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
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestsListServiceImpl implements TestsListService {
    private final TestsListInfoRepository repository;
    private final CompanyTestsRepository testsRepository;

    // 모의시험 문항 가져오기
    @Override
    public HashMap<String, Object> readTestsList(Long companyTestsId) {
        return Maps.newHashMap(ImmutableMap.of(
                "read", infoListToDtoList(
                        Objects.requireNonNull(
                                AllTestsByThat(companyTestsInfoFindById(companyTestsId))))
                , "test", "success"));
    }

    @Override
    public Long save(TestsListDto dto, UserInfo userInfo) {
        if (userInfo.getId().equals(-1L)) return -1L;
        return repository.save(
                        TestsListInfo.builder()
                                .title(dto.getTitle())
                                .contents(dto.getContents())
                                .answer(dto.getAnswer())
                                .tests(
                                        testsRepository.getById(dto.getTests_id())
                                )
                                .isDeleted(false)
                                .build()
                )
                .getId();
    }

    @Override
    public Long update(TestsListDto dto, UserInfo userInfo) {
        final TestsListInfo test = getTest(dto.getTests_id());

        if (userInfo.getId().equals(-1L)) return -1L;
        return repository.save(TestsListInfo.builder()
                        .id(test.getId())
                        .title(dto.getTitle() == null ? test.getTitle() : dto.getTitle())
                        .contents(dto.getContents() == null ? test.getContents() : dto.getContents())
                        .answer(dto.getAnswer() == null ? test.getAnswer() : dto.getAnswer())
                        .tests(test.getTests())
                        .createDate(test.getCreateDate())
                        .updateDate(test.getUpdateDate())
                        .isDeleted(false)
                        .build()
                )
                .getId();
    }


    @Override
    public Long delete(TestsListDto dto, UserInfo userInfo) {
        final TestsListInfo test = getTest(dto.getTests_id());

        if (userInfo.getId().equals(-1L)) return -1L;
        return repository.save(TestsListInfo.builder()
                        .id(test.getId())
                        .title(test.getTitle())
                        .contents(test.getContents())
                        .answer(test.getAnswer())
                        .tests(test.getTests())
                        .createDate(test.getCreateDate())
                        .updateDate(test.getUpdateDate())
                        .isDeleted(true)
                        .build()
                )
                .getId();
    }

    private TestsListInfo getTest(long id){
        return repository.findByTests(
                testsRepository.getById(id)
        );
    }

    // TestsListInfo 를 TestsListDto 로 변환시킵니다.
    private List<TestsListDto> infoListToDtoList(List<TestsListInfo> list) {
        return list.stream().map(TestsListDto::new).collect(Collectors.toList());
    }

    // info find tests by id
    private CompanyTestsInfo companyTestsInfoFindById(Long id) {
        return testsRepository.findById(id).orElseGet(() -> CompanyTestsInfo.builder().build());
    }

    // 등록된 모의 시험의 문항 전부 가져오기
    private List<TestsListInfo> AllTestsByThat(CompanyTestsInfo info) {
        return repository.findAllByTests(info);
    }

}
