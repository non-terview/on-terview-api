package site.askephoenix.restapi.board.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.askephoenix.restapi.annotation.LoginUser;
import site.askephoenix.restapi.board.model.CompanyBoardInfo;
import site.askephoenix.restapi.board.repository.CompanyBoardRepository;
import site.askephoenix.restapi.page.vo.PageMaker;
import site.askephoenix.restapi.page.vo.PageVO;
import site.askephoenix.restapi.user.model.UserInfo;

import java.util.HashMap;

@RestController
@RequestMapping("/board")
@NoArgsConstructor
public class BoardController {

    @Autowired
    private CompanyBoardRepository repo;

    @GetMapping("/test")
    public HashMap<String, Object> testLogin(@LoginUser UserInfo userInfo) {
        return Maps.newHashMap(ImmutableMap.of("UserInfo", userInfo));
    }

    @GetMapping("/list/{pageNum}")
    public HashMap<String, Object> list(@PathVariable int pageNum) {
        PageVO vo = new PageVO();
        vo.setPage(pageNum);
        Pageable pageable = vo.makePageable(0, "seq");
        Page<CompanyBoardInfo> result = repo.findAllByCompanyNameContainingOrderBySeq("번째", pageable);
        return Maps.newHashMap(ImmutableMap.of("pageInfo", new PageMaker(result)));
        //커밋을 위한 주석
    }


}
