package site.askephoenix.restapi.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.evaluation.model.EvaluationDetailInfo;

public interface EvaluationDetailRepository extends JpaRepository<EvaluationDetailInfo, Long> {

}
