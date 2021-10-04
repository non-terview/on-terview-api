package site.askephoenix.restapi.company_test.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

/*
 * 모의 시험 작성 리스트의 문항 내용
 * : 제목, 문제, 정답, 해당 테스트
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tests_list")
public class TestsListInfo {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "answer")
    private String answer;

    @JoinColumn(name = "tests")
    @ManyToOne
    private CompanyTestsInfo tests;


    @CreationTimestamp
    private LocalDate createDate;
    @UpdateTimestamp
    private LocalDate updateDate;

    @Builder
    public TestsListInfo(
            Long id, String title,
            String contents, String answer
    ) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.answer = answer;
    }


}
