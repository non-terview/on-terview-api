package site.askephoenix.restapi.evaluation.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import site.askephoenix.restapi.board.model.BoardInfo;

import javax.persistence.*;
import java.util.Date;

/*
 * 회사가 작성한 면접 평가 기준표 모델입니다.
 * 게시판와 다대일 관계를 가집니다. 다 : 기준표 / 일 : 게시판
 *
 * EvaluationDetail 은 [내용]을 가집니다.
 * 내용( 타입: 객관식/주관식, 예상 답안, 점수 )
 *
 * EvaluationTypeList 는 [내용]의 Type 의 대표적인 객관식, 주관식
 * 등을 입력하기 위한 validate 로 default 인 객관식(0~5 점수), 주관식(blank)를
 * 제외하고 사용자(회사)가 추가적으로 입력한 데이터를 추가로 표시, 입력, 재사용 합니다.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "evaluation")
public class EvaluationInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board")
    private BoardInfo boardInfo;

    @Column(name = "gradations")
    private int gradations;

    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    private Date updateDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public EvaluationInfo(int gradations, Long id) {
        this.gradations = gradations;
        this.id = id;
    }
}
