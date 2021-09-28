package site.askephoenix.restapi.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.board.service.BoardService;

import java.util.stream.IntStream;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardInsertTest {

    @Autowired
    private BoardRepository repo;

    @Autowired
    private BoardService service;

/*    @Test
    public void insertTest() {

        IntStream.range(0,20).forEach(i->{
            BoardInfo companyBoardInfo = BoardInfo.builder().companyName(i+"번째").title(i+"번째 제목").content(i+"번째 내용")
                    .type("joy").writer(i/10+"번째 사용자").build();
            repo.save(companyBoardInfo);
        });
    }*/


    @Test
    public void pagingTypeName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<BoardInfo> page = service.searchCompanyTypeName(0,"3", "joy");
        page.getContent().forEach(System.out::println);
    }

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

    @Test
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

/*
    @Test
    public void somethingTest() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<CompanyBoardInfo> results = repo.findByWriterAndAndCompanyNameContainingOrderBySeq("16", pageable);
        results.getContent().forEach(System.out::println);

    }
*/


}
