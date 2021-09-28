package site.askephoenix.restapi.board.service;

import org.springframework.data.domain.Page;
import site.askephoenix.restapi.board.model.BoardInfo;

import java.util.HashMap;

public interface BoardService {
    Page<BoardInfo> getBoardPage(int pageNum , String companyName);

    Page<BoardInfo> searchCompanyTypeName(int pageNum, String companyName, String companyType);

}
