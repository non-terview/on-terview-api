package site.askephoenix.restapi.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import site.askephoenix.restapi.board.model.CompanyBoardInfo;



public interface CompanyBoardRepository extends CrudRepository<CompanyBoardInfo,Long> {

    //보드 타입으로 검색하는 쿼리입니다
    @Query(value = "select board from  CompanyBoardInfo board where board.type like %?1% order by board.seq desc ")
    public Page<CompanyBoardInfo> searchByType(String type,Pageable pageable);

    //보드 제목(title)로 검색하는 쿼리입니다
    @Query(value = "select board from  CompanyBoardInfo  board where board.title like %?1% order by  board.seq desc ")
    public Page<CompanyBoardInfo> searchByTitle(String title,Pageable pageable);

    //보드 내용으로 검색하는 쿼리입니다
    @Query(value = "select board from  CompanyBoardInfo  board where board.content like %?1% order by  board.seq desc ")
    public Page<CompanyBoardInfo> searchByContent(String content,Pageable pageable);



    //최신 등록글(seq 높은순) 보기위한 jpa 메소드
    public Page<CompanyBoardInfo> findAllBySeqGreaterThanOrderBySeqDesc(Long seq,Pageable pageable);

    //회사 이름으로 검색(정확하게 검색해야함 아니면 % %<<이걸로 감싸줘야함 ) 하기 위한 jpa 메소드
    public Page<CompanyBoardInfo> findAllByCompanyNameLikeOrderBySeqDesc(String name, Pageable pageable);

    //회사 이름으로 검색 하기 위한 jpa 메소드
    public Page<CompanyBoardInfo> findAllByCompanyNameContainingOrderBySeq(String name, Pageable pageable);



}
