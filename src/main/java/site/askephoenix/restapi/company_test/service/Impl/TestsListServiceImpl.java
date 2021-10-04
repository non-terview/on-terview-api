package site.askephoenix.restapi.company_test.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.TestsListDto;
import site.askephoenix.restapi.company_test.model.TestsListInfo;
import site.askephoenix.restapi.company_test.repository.TestsListInfoRepository;
import site.askephoenix.restapi.company_test.service.TestsListService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestsListServiceImpl implements TestsListService {
    private final TestsListInfoRepository repository;

    // 모든 TestsList를 가져옵니다.
    private List<TestsListInfo> AllTestsListInfo(){
        return repository.findAll();
    }

    @Override
    public List<TestsListDto> loadTestsListDto(){
        return AllTestsListInfo().stream().map(TestsListDto::new).collect(Collectors.toList());
    }


}
