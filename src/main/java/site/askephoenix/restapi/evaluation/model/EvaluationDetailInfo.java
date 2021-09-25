package site.askephoenix.restapi.evaluation.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "evaluation_detail_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationDetailInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "evaluation_info")
    @ManyToOne
    private EvaluationInfo evaluationInfo;

    @Column(name = "type")
    private String type;

    @Column(name = "example")
    private String example;

    @Column(name = "score")
    private Integer score;

    @Column(name = "gradation")
    private Integer gradation;

    @Builder
    public EvaluationDetailInfo(
            Long id,
            EvaluationInfo evaluationInfo,
            String type, String example, Integer score, Integer gradation
    ) {
        this.id = id;
        this.evaluationInfo = evaluationInfo;
        this.example = example;
        this.type = type;
        this.score = score;
        this.gradation = gradation;
    }


}
