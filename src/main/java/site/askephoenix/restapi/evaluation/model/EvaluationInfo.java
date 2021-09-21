package site.askephoenix.restapi.evaluation.model;

import lombok.AccessLevel;
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
    private BoardInfo boardInfo;


}
