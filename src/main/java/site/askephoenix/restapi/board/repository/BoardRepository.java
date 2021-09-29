package site.askephoenix.restapi.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import site.askephoenix.restapi.board.model.BoardInfo;


public interface BoardRepository extends CrudRepository<BoardInfo, Long> {

    //보드 타입으로 검색하는 쿼리입니다
    @Query(value = "select board from  BoardInfo board where board.type like %?1% order by board.seq desc ")
    Page<BoardInfo> searchByType(String type, Pageable pageable);

    //보드 제목(title)로 검색하는 쿼리입니다
    @Query(value = "select board from  BoardInfo  board where board.title like %?1% order by  board.seq desc ")
    Page<BoardInfo> searchByTitle(String title, Pageable pageable);

    //보드 내용으로 검색하는 쿼리입니다
    @Query(value = "select board from  BoardInfo  board where board.content like %?1% order by  board.seq desc ")
    Page<BoardInfo> searchByContent(String content, Pageable pageable);

    //타입과 회사이름으로 검색하는 쿼리입니다
    @Query(value = "select board from  BoardInfo board where board.type like %?1% and board.companyName like %?2% order by board.seq desc ")
    Page<BoardInfo> companyTypeName(String companyType, String companyName ,Pageable pageable);


    BoardInfo findBySeq(Long Seq);
    //최신 등록글(seq 높은순) 보기위한 jpa 메소드
    Page<BoardInfo> findAllBySeqGreaterThanOrderBySeqDesc(Long seq, Pageable pageable);

    //회사 이름으로 검색(정확하게 검색해야함 아니면 % %<<이걸로 감싸줘야함 ) 하기 위한 jpa 메소드
    Page<BoardInfo> findAllByCompanyNameLikeOrderBySeqDesc(String name, Pageable pageable);

    //회사 이름으로 검색 하기 위한 jpa 메소드
    Page<BoardInfo> findAllByCompanyNameContainingOrderBySeq(String name, Pageable pageable);

    Page<BoardInfo> findAllByCompanyNameContaining(String name, Pageable pageable);

}
