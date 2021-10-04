package site.askephoenix.restapi.scheduletest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.askephoenix.restapi.schedule.model.ScheduleInfo;
import site.askephoenix.restapi.schedule.repository.ScheduleRepository;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;

import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ScheduleInsertTest {

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private UserRepository userRepository;


/*    @Test
    public void pagingTypeName() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<BoardInfo> page = service.searchByTitle("제목",pageable);
        page.getContent().forEach(System.out::println);
    }*/
/*
    @Test
    public void pageTest() {
        PageDto pageDto = new PageDto();
        pageDto.setSort(PageDto.SORT_ASC);
        pageDto.setRequestPage(0);
        pageDto.setPageSize(20);
        PageVO pageVo = new PageVO();
        Pageable pageable = pageVo.makePageable(pageDto);
        Page<BoardInfo> page = repo.findAllByCompanyNameContaining("", pageable);
        page.getContent().forEach(System.out::println);
    }*/

  /*  @Test
    public void insetUser() {
        IntStream.range(0,3).forEach(i->{
            UserInfo userInfo = UserInfo.builder().name(i+"번째사용자").email(i+"@naver.com").password("1234"+i).build();
            userRepository.save(userInfo);
        });
    }
*/
/*    @Test
    public void insertTest() {

        IntStream.range(0,20).forEach(i->{
            ScheduleInfo info = ScheduleInfo.builder().title("회사"+1).userInfo(userRepository.getById((i/2)+1L)).build();
            repository.save(info);
        });
    }*/


    @Test
    public void findTest() {
        Pageable pageable = PageRequest.of(0, 10);
        UserInfo userInfo = userRepository.getById(1L);
        Page<ScheduleInfo> page = repository.searchByUser(userInfo, pageable);
        page.getContent().forEach(System.out::println);
    }


/*
    @Test
    public void pagingTypeName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<BoardInfo> page = service.searchCompanyTypeName(0,"3", "joy");
        page.getContent().forEach(System.out::println);
    }*/

/*    @Test
    public void pageTest2() {
        PageDto pageDto = new PageDto();
        pageDto.setSort(PageDto.SORT_ASC);
        pageDto.setRequestPage(0);
        pageDto.setPageSize(20);
        PageVO pageVo = new PageVO();
        Pageable pageable = pageVo.makePageable(pageDto);
        Page<BoardInfo> page = repo.findByType("1","1", pageable);
        page.getContent().forEach(System.out::println);
    }*/


/*    @Test
    public void pageTest(){
        Page<BoardInfo> page = service.getBoardPage(0, "번째");
        page.getContent().forEach(System.out::println);
        System.out.println(page.getTotalElements());
    }*/

/*    @Test
    public void pageTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CompanyBoardInfo> results = repo.findAllBySeqGreaterThanOrderBySeqDesc(10L,pageable);
        results.getContent().forEach(System.out::println);
      *//*  results.forEach(companyBoardInfo -> {
            System.out.println(companyBoardInfo.toString());
        });*//*
    }*/


    //타입으로 검색하기
/*    @Test
    public void searchTypeTest(){
        Pageable pageable = PageRequest.of(0,20);
        Page<CompanyBoardInfo> results = repo.searchByType("전자", pageable);
        results.getContent().forEach(System.out::println);
    }*/

    //타이틀로 하기
   /* @Test
    public void searchTitleTest() {
        Pageable pageable = PageRequest.of(0,20);
        Page<CompanyBoardInfo> results = repo.searchByTitle("15번째", pageable);
        results.getContent().forEach(System.out::println);
    }

    //내용으로 검색
    @Test
    public void searchContentTest() {
        Pageable pageable = PageRequest.of(0,20);
        Page<CompanyBoardInfo> results = repo.searchByContent("17번째 내용", pageable);
        results.getContent().forEach(System.out::println);
    }
*/

  /*  @Test
    public void searByCompanyName() {
        Pageable pageable = PageRequest.of(0, 20);
        // like 로 사용하려면 % %<<이것을 넣어줘야만 포함됨
        Page<BoardInfo> results = repo.findAllByCompanyNameLikeOrderBySeqDesc("%19%", pageable);
        results.get().forEach(System.out::println);
    }

    @Test
    public void searchByCompanyNameContaining() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<BoardInfo> results = repo.findAllByCompanyNameContainingOrderBySeq("16", pageable);
        results.get().forEach(System.out::println);
    }
*/
/*
    @Test
    public void somethingTest() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<CompanyBoardInfo> results = repo.findByWriterAndAndCompanyNameContainingOrderBySeq("16", pageable);
        results.getContent().forEach(System.out::println);

    }
*/


}
