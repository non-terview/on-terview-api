package site.askephoenix.restapi.board.service;

import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

public interface BoardService {
    HashMap<String,Object> getBoardPage(int pageNum);

    HashMap<String, Object> hashTest(UserInfo userInfo);
}
