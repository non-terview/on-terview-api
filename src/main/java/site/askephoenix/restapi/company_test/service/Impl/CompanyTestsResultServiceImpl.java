package site.askephoenix.restapi.company_test.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.company_test.dto.ResultDto;
import site.askephoenix.restapi.company_test.model.CompanyTestsInfo;
import site.askephoenix.restapi.company_test.model.CompanyTestsResultInfo;
import site.askephoenix.restapi.company_test.repository.CompanyTestsRepository;
import site.askephoenix.restapi.company_test.repository.CompanyTestsResultRepository;
import site.askephoenix.restapi.company_test.service.CompanyTestsResultService;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTestsResultServiceImpl implements CompanyTestsResultService {
    private CompanyTestsResultRepository repository;
    private CompanyTestsRepository testsRepository;
    private UserRepository userRepository;

    @Override
    public HashMap<String, Object> readResultByTester(UserInfo tester){
        return Maps.newHashMap(ImmutableMap.of(
                "read", allResultByTester(tester),
                "test", "success"
        ));
    }

    // 응시한 결과를 저장합니다.
    @Override
    public Long save(Long test, ResultDto dto, UserInfo userInfo) {
        if (userInfo == null) return -1L;
        CompanyTestsResultInfo resultInfo = repository.save(
                CompanyTestsResultInfo.builder()
                        .tests( testsRepository.getById( test ) )
                        .tester( userRepository.getById( dto.getTester().getId() ) )
                        .sort_num( dto.getSort_num() )
                        .title( dto.getTitle() )
                        .answer(dto.getAnswer())
                        .build()
        );

        return resultInfo.getId();
    }

    @Override
    public Long update(Long test, ResultDto dto, UserInfo userInfo) {
        final CompanyTestsResultInfo info = repository.findByTests(
                testsRepository.getById(
                        test
                )
        );
        if (userInfo == null) return -1L;
        CompanyTestsResultInfo resultInfo = repository.save(
                CompanyTestsResultInfo.builder()
                        .id( info.getId() )
                        .tests( info.getTests() )
                        .tester( info.getTester() )
                        .sort_num( info.getSort_num() )
                        .title( info.getTitle() )
                        .answer(dto.getAnswer() == null ? info.getAnswer() : dto.getAnswer() )
                        .createDate( info.getCreateDate() )
                        .updateDate( info.getUpdateDate() )
                        .build()
        );

        return resultInfo.getId();
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
