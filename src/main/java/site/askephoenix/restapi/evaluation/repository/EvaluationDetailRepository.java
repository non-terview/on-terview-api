package site.askephoenix.restapi.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.evaluation.model.EvaluationDetailInfo;
import site.askephoenix.restapi.evaluation.model.EvaluationInfo;

import java.util.Optional;

public interface EvaluationDetailRepository extends JpaRepository<EvaluationDetailInfo, Long> {
    Optional<EvaluationDetailInfo> findByEvaluationInfoAndGradation(
            EvaluationInfo evaluationInfo, int gradations
    );
}
