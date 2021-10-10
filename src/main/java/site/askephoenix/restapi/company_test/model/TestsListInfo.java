package site.askephoenix.restapi.company_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 문항의 제목
    @Column(name = "title")
    private String title;

    // 문항 내용
    @Column(name = "contents")
    private String contents;

    // 문항 정답
    @Column(name = "answer")
    private String answer;

    // 문항 등록 정보
    @JoinColumn(name = "tests")
    @ManyToOne
    private CompanyTestsInfo tests;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public TestsListInfo(
            Long id, String title,
            String contents, String answer, CompanyTestsInfo tests,
            LocalDateTime createDate, LocalDateTime updateDate,
            boolean isDeleted
    ) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.answer = answer;
        this.tests = tests;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isDeleted = isDeleted;
    }


}
