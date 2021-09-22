package site.askephoenix.restapi.evaluation.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.askephoenix.restapi.board.model.BoardInfo;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "evaluation")
public class EvaluationInfo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board")
    private BoardInfo boardInfo;

    @Column(name="gradations")
    private int gradations;

    @Builder
    public EvaluationInfo(int gradations, Long id){
        this.gradations = gradations;
        this.id = id;
    }
}
