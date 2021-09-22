package site.askephoenix.restapi.evaluation.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "evaluation_detail_info_type")
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

}
