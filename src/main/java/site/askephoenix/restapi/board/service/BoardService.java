package site.askephoenix.restapi.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import site.askephoenix.restapi.board.model.BoardInfo;

import java.util.HashMap;

public interface BoardService {
   /* Page<BoardInfo> getBoardPage(int pageNum , String companyName);

    Page<BoardInfo> searchCompanyTypeName(int pageNum, String companyName, String companyType);
*/

    //타이틀로 검색하는 메소드
    Page<BoardInfo> searchByTitle(String keyword, Pageable pageable);

    //내용으로로 검색하는 메소드
    Page<BoardInfo> searchByContent(String keyword, Pageable pageable);

    //회사이름으로 검색하는 메소드
    Page<BoardInfo> searchByCompanyName(String keyword, Pageable pageable);

    //내용과 제목으로 검색하는 메소드
    Page<BoardInfo> searchByTitleContent(String keyword, Pageable pageable);


}