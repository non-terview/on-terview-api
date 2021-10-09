package site.askephoenix.restapi.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import site.askephoenix.restapi.board.model.BoardInfo;
import site.askephoenix.restapi.category.model.CategoryInfo;
import site.askephoenix.restapi.user.model.UserInfo;


public interface BoardRepository extends CrudRepository<BoardInfo, Long> {


    BoardInfo findBySeq(Long Seq);
    //최신 등록글(seq 높은순) 보기위한 jpa 메소드
    Page<BoardInfo> findAllBySeqGreaterThanOrderBySeqDesc(Long seq, Pageable pageable);

    //회사 이름으로 검색(정확하게 검색해야함 아니면 % %<<이걸로 감싸줘야함 ) 하기 위한 jpa 메소드
    Page<BoardInfo> findAllByCompanyNameLikeOrderBySeqDesc(String name, Pageable pageable);

    //회사 이름으로 검색 하기 위한 jpa 메소드
    Page<BoardInfo> findAllByCompanyNameContainingOrderBySeq(String name, Pageable pageable);

    Page<BoardInfo> findAllByCompanyNameContaining(String name, Pageable pageable);

    // 쿼리문을 사용한것들은 search 키워드를 사용하겠습니다

    //보드 타입으로 검색하는 쿼리입니다
    @Query(value = "select board from BoardInfo board where board.category = ?1 and board.isDeleted = false ")
    Page<BoardInfo> searchByType(CategoryInfo categoryId, Pageable pageable);

    //타이틀로 검색하는 쿼리
    @Query(value = "select board from  BoardInfo board where board.title like %?1% and board.isDeleted = false")
    Page<BoardInfo> searchByTitle(String keyword, Pageable pageable);

    //내용으로 검색하는 쿼리
    @Query(value = "select board from  BoardInfo board where board.content like %?1% and board.isDeleted = false")
    Page<BoardInfo> searchByContent(String keyword, Pageable pageable);

    //회사 이름으로 검색하는 쿼리
    @Query(value = "select board from  BoardInfo board where board.companyName like %?1% and board.isDeleted = false ")
    Page<BoardInfo> searchByCompanyName(String keyword, Pageable pageable);

    //제목과 내용으로 검색하는 쿼리입니다
    @Query(value ="select b from BoardInfo b where (b.title like %?1% or b.content like %?1%) and b.isDeleted =false ")
    Page<BoardInfo> searchByTitleContent(String keyword, Pageable pageable);

    //지금은 아니지만 BoardInfo 에 writer 가 String 이 아닌 UserInfo로 변경될경우에는 UserInfo 값을 통해서
    //자신이 쓴(글)을 조회할수있게 해주는 기능 입니다 . 나중에는 type(categoryInfo)정보도 필요하면 말해주세요
    /*
    @Query(value = "select board from BoardInfo board where board.userInfo = ?1 and board.isDeleted = false ")
    Page<BoardInfo> searchSelfBoardPage(UserInfo userInfo, Pageable pageable);
    */

}
