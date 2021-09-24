package site.askephoenix.restapi.evaluation.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "evaluation_detail_info_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationTypeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "evaluation_info_detail")
    @ManyToOne
    private EvaluationDetailInfo evaluationDetailInfo;

    @Column(name = "name")
    private String name;

    @Column(name = "gradations")
    private int gradations;

    @Builder
    public EvaluationTypeList(
            EvaluationDetailInfo evaluationDetailInfo,
            String name, int gradations
    ) {
        this.evaluationDetailInfo = evaluationDetailInfo;
        this.name = name;
        this.gradations = gradations;
    }

}
