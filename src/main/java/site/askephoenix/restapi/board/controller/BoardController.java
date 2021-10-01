package site.askephoenix.restapi.board.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.board.service.BoardService;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

 /*   @GetMapping("/test")
    public HashMap<String, Object> testLogin(@LoginUser UserInfo userInfo) {
        return Maps.newHashMap(ImmutableMap.of("UserInfo", userInfo));
    }*/
/*
    @GetMapping("/list/{pageNum}")
    public HashMap<String, Object> list(@PathVariable(value = "pageNum") int pageNum) {
        return boardService.getBoardPage(pageNum);
    }*/


}
