package site.askephoenix.restapi.board.service;

import java.util.HashMap;

public interface BoardService {
    HashMap<String,Object> getBoardPage(int pageNum);
}
