package site.askephoenix.restapi.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.evaluation.model.EvaluationDetailInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationTypeList;

import java.util.List;
import java.util.Optional;

public interface EvaluationTypeListRepository extends JpaRepository<EvaluationTypeList, Long> {
    Optional<List<EvaluationTypeList>> findAllByEvaluationDetailInfo(
            EvaluationDetailInfo evaluationDetailInfo
    );
}
