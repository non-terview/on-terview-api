package site.askephoenix.restapi.scheduletest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public void scheduleSearchTest() {
        Pageable pageable = PageRequest.of(0, 10);
        UserInfo userInfo = userRepository.findAllById(1L);
        Page<ScheduleInfo> page = repository.searchByUser(userInfo, pageable);
        page.getContent().forEach(System.out::println);
    }
*/

    @Test
    public void searchTitleTest() {
        //Pageable 에서 솔팅(정렬)을 하기위해서는 properties 가 1개이상 필요함 (정렬의 기준이 되어줄것 이코드 일
        //경우에는 id를(seq)를 기준으로 정렬하였음)
        Pageable pageable = PageRequest.of(0, 10 , Sort.Direction.DESC , "id");
        UserInfo userInfo = userRepository.searchById(5L);
        Page<ScheduleInfo> page = repository.searchScheduleByTitleContent(userInfo, "title", pageable);
        page.getContent().forEach(scheduleInfo -> {
            System.out.print("User ID:"+scheduleInfo.getUserInfo().getId()+"         |");
            System.out.println(scheduleInfo.toString());
        });
    }

    /*@Test
    public void scheduleTest() {
        UserInfo userInfo = userRepository.getById(1L);

    }*/


//생성을 위한테스트 코드
/*    @Test
    public void insertTest() {
        IntStream.range(0,22).forEach(i->{
            repository.save(ScheduleInfo.builder().content(i+"번째 내용임").title(i+"번째 스케쥴의 title").userInfo(userRepository.getById(3L)).memo(i+"번째 메모").build());
            repository.save(ScheduleInfo.builder().content(i+"번째 내용임").title(i+"번째 스케쥴의 title").userInfo(userRepository.getById(4L)).memo(i+"번째 메모").build());
            repository.save(ScheduleInfo.builder().content(i+"번째 내용임").title(i+"번째 스케쥴의 title").userInfo(userRepository.getById(5L)).memo(i+"번째 메모").build());

        });
    }*/
/*
    @Test
    public void findTest() {
        Pageable pageable = PageRequest.of(0, 10);
        UserInfo userInfo = userRepository.getById(1L);
        Page<ScheduleInfo> page = repository.searchByUser(userInfo, pageable);
        page.getContent().forEach(System.out::println);
    }*/


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
