package site.askephoenix.restapi.company_test.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsResultRepository;
import site.askephoenix.restapi.company_test.repository.TestsListInfoRepository;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTestsResultServiceImpl implements CompanyTestsResultService {
    private final CompanyTestsResultRepository repository;
    private final TestsListInfoRepository listRepository;
    private final UserRepository userRepository;

    @Override
    public List<ResultDto> readResultByTester(UserInfo tester) {
        return infoListToDtoList(allResultByTester(tester));
    }

    // 응시한 결과를 저장합니다.
    @Override
    public List<Long> save(Long test, List<ResultDto> resultDtoList, UserInfo userInfo) {
        if (userInfo == null) return Collections.singletonList(-1L);
        return resultDtoList.stream().map(
                dto ->
                        repository.save(
                                CompanyTestsResultInfo.builder()
                                        .list(listRepository.getById(test))
                                        .tester(userRepository.getById(userInfo.getId()))
                                        .sort_num(dto.getSort_num())
                                        .title(dto.getTitle())
                                        .answer(dto.getAnswer())
                                        .isDeleted(false)
                                        .build()
                        ).getId()

        ).collect(Collectors.toList());

    }

    @Override
    public Long update(Long result, ResultDto dto, UserInfo userInfo) {
        final CompanyTestsResultInfo info = getTestResult(result);
        if (userInfo == null) return -1L;
        return info.getId() == null ? -2L : repository.save(
                CompanyTestsResultInfo.builder()
                        .id(info.getId())
                        .list(info.getList())
                        .tester(info.getTester())
                        .sort_num(info.getSort_num())
                        .title(info.getTitle())
                        .answer(dto.getAnswer() == null ? info.getAnswer() : dto.getAnswer())
                        .createDate(info.getCreateDate())
                        .updateDate(info.getUpdateDate())
                        .isDeleted(false)
                        .build()
        ).getId();
    }

    @Override
    public Long delete(Long result, UserInfo userInfo) {
        final CompanyTestsResultInfo info = getTestResult(result);
        if (userInfo == null) return -1L;
        return info.getId() == null ? -2L : repository.save(
                CompanyTestsResultInfo.builder()
                        .id(info.getId())
                        .list(info.getList())
                        .tester(info.getTester())
                        .sort_num(info.getSort_num())
                        .title(info.getTitle())
                        .answer(info.getAnswer())
                        .createDate(info.getCreateDate())
                        .updateDate(info.getUpdateDate())
                        .isDeleted(true)
                        .build()
        ).getId();
    }

    private CompanyTestsResultInfo getTestResult(Long test) {
        return repository.searchById(
                test
        ).orElseGet(() -> CompanyTestsResultInfo.builder().build());
    }

    // 사용자 결과물 가져오기
    private List<CompanyTestsResultInfo> allResultByTester(UserInfo tester) {
        return repository.AllByTester(tester).orElseGet(Collections::emptyList);
    }

    // CompanyTestsResultInfo 를 ResultDto 로 변환시킵니다.
    private List<ResultDto> infoListToDtoList(List<CompanyTestsResultInfo> list) {
        return list.stream().map(ResultDto::new).collect(Collectors.toList());
    }

}
