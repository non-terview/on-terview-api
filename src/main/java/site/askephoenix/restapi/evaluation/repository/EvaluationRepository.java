package site.askephoenix.restapi.evaluation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.board.model.CompanyBoardInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;

import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<EvaluationInfo, Long> {
    Optional<EvaluationInfo> findByCompanyBoardInfo(CompanyBoardInfo companyBoardInfo);

}
