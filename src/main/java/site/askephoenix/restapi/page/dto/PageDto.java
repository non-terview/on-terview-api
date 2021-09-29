package site.askephoenix.restapi.page.dto;


import lombok.Getter;
import lombok.Setter;
import site.askephoenix.restapi.user.dto.UserInfoDto;

@Getter
@Setter
public class PageDto {
    public final static int SORT_DESC = 0;
    public final static int SORT_ASC = 1;

    //요청하는 페이지
    private int requestPage;
    //한페이당 볼 갯수
    private int pageSize;
    //혹시 모를 유저
    private UserInfoDto userInfoDto;
    private int sort = 0 ;

}
