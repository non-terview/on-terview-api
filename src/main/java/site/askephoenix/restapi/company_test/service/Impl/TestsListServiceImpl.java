package site.askephoenix.restapi.company_test.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.model.TestsListInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsRepository;
import site.askephoenix.restapi.company_test.repository.TestsListInfoRepository;
import site.askephoenix.restapi.company_test.service.TestsListService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestsListServiceImpl implements TestsListService {
    private final TestsListInfoRepository repository;
    private final CompanyTestsRepository testsRepository;

    // 모의시험 문항 가져오기
    @Override
    public List<TestsListDto> readTestsList(Long companyTestsId) {
        return infoListToDtoList(AllTestsByThat(companyTestsInfoFindById(companyTestsId)));
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
        final TestsListInfo listInfo = repository.findById(dto.getId()).orElseGet(
                () -> TestsListInfo.builder().build()
        );

        if (listInfo.getId() == null) return -2L;
        if (userInfo.getId().equals(-1L)) return -1L;
        return repository.save(TestsListInfo.builder()
                        .id(dto.getId())
                        .title(dto.getTitle() == null ? listInfo.getTitle() : dto.getTitle())
                        .contents(dto.getContents() == null ? listInfo.getContents() : dto.getContents())
                        .answer(dto.getAnswer() == null ? listInfo.getAnswer() : dto.getAnswer())
                        .tests(listInfo.getTests())
                        .createDate(listInfo.getCreateDate())
                        .updateDate(listInfo.getUpdateDate())
                        .isDeleted(false)
                        .build()
                )
                .getId();
    }


    @Override
    public Long delete(Long list, UserInfo userInfo) {
        final TestsListInfo test = repository.findById(list).orElseGet(() -> TestsListInfo.builder().build());

        if (userInfo.getId().equals(-1L)) return -1L;
        return test.getId() == null ? -2L : repository.save(TestsListInfo.builder()
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
        return info.getId() == null ? Collections.emptyList() : repository.searchAllByTests(info).orElseGet(Collections::emptyList);
    }

}
