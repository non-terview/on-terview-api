package site.askephoenix.restapi.evaluation.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "evaluation_detail_info")
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


}
