package site.askephoenix.restapi.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.askephoenix.restapi.evaluation.model.EvaluationTypeList;

public interface EvaluationTypeListRepository extends JpaRepository<EvaluationTypeList, Long> {

}
