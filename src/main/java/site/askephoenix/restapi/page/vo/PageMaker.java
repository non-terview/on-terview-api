package site.askephoenix.restapi.page.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "pageList")

public class PageMaker {
    final static int DEFAULT_PAGING_NUM = 10;
    private Page results ;
    //앞으로
    private Pageable prevPage;
    //뒤로우
    private Pageable nextPage;
    //현재 페이지숫자
    private int currentPageNum;
    //총 페이지숫자
    private int totalPageNum;
    //현재 페이지 정보
    private Pageable currentPage;
    private List<Pageable> pageableList;

    public PageMaker(Page results){
        this.results = results;
        this.currentPage = results.getPageable();
        //페이지는 시작이 0 이기때문에 pageable 에서 받은 페이지는 +1을 해줘야 클라이언트의 페이지숫자가됨
        this.currentPageNum = currentPage.getPageNumber() + 1;
        this.totalPageNum = results.getTotalPages();
        this.pageableList = new ArrayList<>();

    }

    //페이징에는 이게 핵심입니다 .
    //저는열심히 책을 배꼇어요!@
    private void calcPages() {
        int tempEndNum =  (int)(Math.ceil(this.currentPageNum/10.0)*10);
        int startNum =  tempEndNum - 9;

        Pageable startPage = this.currentPage;

        //move  to start Pageable
        //previousOrFirst()<<이 메소드는 이전페이지 정보를 리턴 , 단 처음일경우 지금꺼 리턴
        for (int i = startNum; i < this.currentPageNum; i++) {
            startPage = startPage.previousOrFirst();
        }

        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        //만약 tempEndNum 이 totalPageNum 보다 클경우(tempEndNum 은 현재 (페이지+1/10.0)*10 이기 때문에 더 클수도있음
        if (this.totalPageNum < tempEndNum) {
            tempEndNum = this.totalPageNum;
            this.nextPage = null;
        }
        for (int i = startNum; i <= tempEndNum; i++) {
            pageableList.add(startPage);
            startPage = startPage.next();
        }
        //위에서 페이지 전체를 받았음 ex)1~10까지의 페이지 현재 startPage 의 값에는 마지막 페이지값이있음
        //만약에 이다음페이지가 총페이지보다클경우 다음 페이지는
        this.nextPage = startPage.getPageNumber() +1 <totalPageNum? startPage:null;



    }

}
