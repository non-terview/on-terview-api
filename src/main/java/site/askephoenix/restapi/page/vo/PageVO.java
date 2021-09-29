package site.askephoenix.restapi.page.vo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import site.askephoenix.restapi.page.dto.PageDto;

public class PageVO {
    //기본 페이지당 갯수
    private static final int DEFAULT_SIZE = 20;
    //최대 페이지당 갯수
    private static final int MAX_SIZE = 50;
    private int page;
    private int size;

    public PageVO() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
    }



    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        //요청 페이지가 0이하라면 1을 세팅 아니면 페이지를 세팅
        this.page = page <= 0 ? 1 : page;
    }

    //페이지당 사이즈 갯수 설정
    //만약에 위에 정의 되어있는 최대 사이즈보다 크거나 기본 사이즈보다 작을 경우는 기본 사이즈를 지정(이거 안하면 사이트 약해짐)
    //최대와 기본 사이의 숫자로 지정하면 해줌
    public void setSize(int size) {
        this.size = size < DEFAULT_SIZE || size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public int getSize() {
        return size;
    }

    public Pageable makePageable(PageDto pageDto) {
        setPage(pageDto.getRequestPage());
        setSize(pageDto.getPageSize());
        Sort.Direction dir = pageDto.getSort() == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(this.page - 1, this.size, dir ,"seq");
    }

    public Pageable makePageable(int direction, String... props) {
        Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;

        return PageRequest.of(this.page - 1, this.size, dir, props);
    }

}
