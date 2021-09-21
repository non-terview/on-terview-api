package site.askephoenix.restapi.board.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.board.repository.BoardRepository;
import site.askephoenix.restapi.board.service.BoardService;
import site.askephoenix.restapi.page.vo.PageMaker;
import site.askephoenix.restapi.page.vo.PageVO;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repo;

    @Override
    public HashMap<String,Object> getBoardPage( int pageNum ){
        PageVO vo = new PageVO();
        vo.setPage(pageNum);
        Pageable pageable = vo.makePageable(0, "seq");
        Page<BoardInfo> result = repo.findAllByCompanyNameContainingOrderBySeq("번째", pageable);
        return Maps.newHashMap(ImmutableMap.of("pageInfo", new PageMaker(result)));
    }
}
