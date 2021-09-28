package site.askephoenix.restapi.board.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<BoardInfo> getBoardPage(int pageNum, String companyName) {
        Pageable pageable = PageRequest.of(pageNum, 20);
        return repo.findAllByCompanyNameContainingOrderBySeq(companyName, pageable);
    }

    @Override
    public Page<BoardInfo> searchCompanyTypeName(int pageNum, String companyName, String companyType) {
        Pageable pageable = PageRequest.of(pageNum, 20);

        return repo.companyTypeName(companyType, companyName ,pageable);
    }




  /*  @Override
    public HashMap<String,Object> getBoardPage(int pageNum ,String companyName){
        PageVO vo = new PageVO();
        vo.setPage(pageNum);
        Pageable pageable = vo.makePageable(0, "seq");
        Page<BoardInfo> result = repo.findAllByCompanyNameContainingOrderBySeq(companyName, pageable);
        return Maps.newHashMap(ImmutableMap.of("pageInfo", new PageMaker(result)));
    }*/
}
